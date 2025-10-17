import pika
import json
import requests
import logging
import os
import threading
import time
from datetime import datetime, timedelta
from dotenv import load_dotenv

# === CARREGA VARIÁVEIS DO ARQUIVO .env (SE EXISTIR) ===
load_dotenv()

# === CONFIGURAÇÕES ===
RABBITMQ_HOST = os.getenv('RABBITMQ_HOST', 'localhost')
RABBITMQ_QUEUE = os.getenv('RABBITMQ_QUEUE', 'fila-emails')  # fila de emails imediatos
RABBITMQ_QUEUE_RELATORIO = os.getenv('RABBITMQ_QUEUE_RELATORIO', 'fila-relatorio')  # nova fila para relatórios
RABBITMQ_USER = os.getenv('RABBITMQ_USER', 'guest')
RABBITMQ_PASS = os.getenv('RABBITMQ_PASS', 'guest')

BREVO_API_KEY = os.getenv('BREVO_API_KEY')
DESTINATARIO_EMAIL = os.getenv('DESTINATARIO_EMAIL')

# === LOGS ===
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

# === ARMAZENAMENTO TEMPORÁRIO EM MEMÓRIA ===
vendas_do_dia = {}

# =========================================================
# === ENVIO DE E-MAIL INDIVIDUAL (FILA PRINCIPAL)
# =========================================================
def enviar_email_brevo(dados):
    """Envia o email via API Brevo (venda individual)."""
    url = "https://api.brevo.com/v3/smtp/email"

    saida = dados["saidaEstoque"]
    item = dados["itemEstoque"]
    lote = dados["lote"]
    funcionario = dados["funcionario"]

    horario = saida.get("horarioSaida")
    if isinstance(horario, list) and len(horario) >= 6:
        horario_formatado = f"{horario[2]:02d}/{horario[1]:02d}/{horario[0]} {horario[3]:02d}:{horario[4]:02d}:{horario[5]:02d}"
    else:
        horario_formatado = str(horario)

    preco = item.get("preco")
    preco_formatado = f"R$ {preco:.2f}" if isinstance(preco, (int, float)) else "preço não informado"

    assunto = f"Venda do item {item['descricao']}"
    corpo_email = f"""
    O Item "{item['descricao']}" do lote ID {lote['idLote']} foi vendido às "{horario_formatado}"
    pelo valor de {preco_formatado}.
    Nova quantidade em estoque: {item['qtdArmazenado']}.
    
    Vendedor:
    Nome: {funcionario['nome']}
    E-mail: {funcionario['email']}
    Telefone: {funcionario['telefone']}
    """

    payload = {
        "sender": {"name": "Sistema de Estoque", "email": "fernandoalmeida.mda@gmail.com"},
        "to": [{"email": DESTINATARIO_EMAIL}],
        "subject": assunto,
        "textContent": corpo_email.strip()
    }

    headers = {
        "accept": "application/json",
        "api-key": BREVO_API_KEY,
        "content-type": "application/json"
    }

    try:
        response = requests.post(url, json=payload, headers=headers)
        response.raise_for_status()
        logging.info(f"✅ E-mail individual enviado para {DESTINATARIO_EMAIL}")
    except Exception as e:
        logging.error(f"❌ Erro ao enviar e-mail individual: {e}")

# =========================================================
# === RELATÓRIO DIÁRIO DE VENDAS (NOVA FILA)
# =========================================================
def enviar_relatorio_diario():
    """Publica um relatório consolidado de todas as vendas do dia na nova fila."""
    global vendas_do_dia
    if not vendas_do_dia:
        logging.info("ℹ️ Nenhuma venda registrada no dia. Nenhum e-mail de relatório enviado.")
        return

    # Monta corpo do relatório
    data_atual = datetime.now().strftime("%d/%m/%Y")
    total_geral = sum(item["quantidade"] * item["preco"] for item in vendas_do_dia.values())
    corpo = f"RELATÓRIO DE VENDAS - {data_atual}\n\n"
    for descricao, info in vendas_do_dia.items():
        subtotal = info["quantidade"] * info["preco"]
        corpo += f"- {descricao}: {info['quantidade']} unidades vendidas, total R$ {subtotal:.2f}\n"
    corpo += f"\nTotal geral: R$ {total_geral:.2f}"

    # Conecta ao RabbitMQ e publica o relatório na fila-relatorio
    try:
        credentials = pika.PlainCredentials(RABBITMQ_USER, RABBITMQ_PASS)
        connection = pika.BlockingConnection(pika.ConnectionParameters(host=RABBITMQ_HOST, credentials=credentials))
        channel = connection.channel()
        channel.queue_declare(queue=RABBITMQ_QUEUE_RELATORIO, durable=True)

        mensagem = {"data": data_atual, "conteudo": corpo}
        channel.basic_publish(
            exchange='',
            routing_key=RABBITMQ_QUEUE_RELATORIO,
            body=json.dumps(mensagem),
            properties=pika.BasicProperties(delivery_mode=2)
        )
        logging.info(f"📤 Relatório diário publicado na fila '{RABBITMQ_QUEUE_RELATORIO}'.")
        connection.close()
        vendas_do_dia = {}
    except Exception as e:
        logging.error(f"❌ Erro ao publicar relatório diário no RabbitMQ: {e}")

# =========================================================
# === CONSUMIDOR DO RELATÓRIO (ENVIA O E-MAIL FINAL)
# =========================================================
def consumidor_relatorio():
    """Consome mensagens da fila de relatórios e envia o e-mail final."""
    def callback_relatorio(ch, method, properties, body):
        try:
            dados = json.loads(body.decode('utf-8'))
            data_atual = dados["data"]
            corpo = dados["conteudo"]

            url = "https://api.brevo.com/v3/smtp/email"
            payload = {
                "sender": {"name": "Sistema de Estoque", "email": "fernandoalmeida.mda@gmail.com"},
                "to": [{"email": DESTINATARIO_EMAIL}],
                "subject": f"Relatório Diário de Vendas - {data_atual}",
                "textContent": corpo
            }
            headers = {
                "accept": "application/json",
                "api-key": BREVO_API_KEY,
                "content-type": "application/json"
            }

            response = requests.post(url, json=payload, headers=headers)
            response.raise_for_status()
            logging.info("📊 Relatório diário enviado com sucesso por e-mail!")
        except Exception as e:
            logging.error(f"❌ Erro ao enviar relatório diário: {e}")

    threading.Thread(target=lambda: iniciar_consumidor_fila(RABBITMQ_QUEUE_RELATORIO, callback_relatorio), daemon=True).start()

# =========================================================
# === AGENDAMENTO DIÁRIO
# =========================================================
def agendar_relatorio_diario(hora_envio=23, minuto_envio=59):
    """Agenda o envio do relatório diário para a hora especificada."""
    def ciclo():
        while True:
            agora = datetime.now()
            proximo_envio = agora.replace(hour=hora_envio, minute=minuto_envio, second=0, microsecond=0)
            if proximo_envio <= agora:
                proximo_envio += timedelta(days=1)

            segundos_ate_envio = (proximo_envio - agora).total_seconds()
            horas, resto = divmod(segundos_ate_envio, 3600)
            minutos, _ = divmod(resto, 60)
            logging.info(f"🕒 Próximo relatório diário será enviado em {int(horas)}h {int(minutos)}min.")
            time.sleep(segundos_ate_envio)
            enviar_relatorio_diario()
            time.sleep(24 * 60 * 60)
    threading.Thread(target=ciclo, daemon=True).start()

# =========================================================
# === FUNÇÃO GENÉRICA DE CONSUMO
# =========================================================
def iniciar_consumidor_fila(fila, callback):
    """Função genérica para consumir uma fila específica."""
    try:
        credentials = pika.PlainCredentials(RABBITMQ_USER, RABBITMQ_PASS)
        connection = pika.BlockingConnection(pika.ConnectionParameters(host=RABBITMQ_HOST, credentials=credentials))
        channel = connection.channel()
        channel.queue_declare(queue=fila, durable=True)
        channel.basic_consume(queue=fila, on_message_callback=callback, auto_ack=True)
        logging.info(f"✅ Consumindo fila '{fila}'...")
        channel.start_consuming()
    except Exception as e:
        logging.error(f"❌ Erro no consumidor da fila '{fila}': {e}")

# =========================================================
# === CALLBACK PRINCIPAL (PROCESSA VENDAS INDIVIDUAIS)
# =========================================================
def callback(ch, method, properties, body):
    """Processa novas mensagens da fila principal."""
    try:
        mensagem = json.loads(body.decode('utf-8'))
        logging.info(f"📩 Mensagem recebida: {mensagem}")
        enviar_email_brevo(mensagem)

        item = mensagem["itemEstoque"]
        descricao = item["descricao"]
        preco = float(item.get("preco", 0))
        if descricao not in vendas_do_dia:
            vendas_do_dia[descricao] = {"quantidade": 1, "preco": preco}
        else:
            vendas_do_dia[descricao]["quantidade"] += 1
    except Exception as e:
        logging.error(f"❌ Erro ao processar mensagem: {e}")

# =========================================================
# === CONSUMIDOR PRINCIPAL
# =========================================================
def iniciar_consumidor():
    """Conecta ao RabbitMQ e inicia o consumo da fila principal."""
    try:
        threading.Thread(target=consumidor_relatorio, daemon=True).start()
        agendar_relatorio_diario(21, 25)  # Exemplo: envia às 21h00
        iniciar_consumidor_fila(RABBITMQ_QUEUE, callback)
    except Exception as e:
        logging.error(f"❌ Erro RabbitMQ: {e}")

# =========================================================
# === EXECUÇÃO PRINCIPAL
# =========================================================
if __name__ == "__main__":
    if not BREVO_API_KEY or not DESTINATARIO_EMAIL:
        print("❌ Faltando variáveis de ambiente: BREVO_API_KEY e/ou DESTINATARIO_EMAIL.")
        exit(1)
    iniciar_consumidor()
