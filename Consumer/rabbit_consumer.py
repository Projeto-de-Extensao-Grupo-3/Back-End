import pika
import json
import requests
import logging
import os
import threading
import time
from datetime import datetime, timedelta
from dotenv import load_dotenv

# === CARREGA VARIÁVEIS DO ARQUIVO .env ===
load_dotenv()

# === CONFIGURAÇÕES ===
RABBITMQ_HOST = os.getenv('RABBITMQ_HOST', 'localhost')
RABBITMQ_QUEUE = os.getenv('RABBITMQ_QUEUE', 'fila-emails')  # fila de emails imediatos
RABBITMQ_QUEUE_RELATORIO = os.getenv('RABBITMQ_QUEUE_RELATORIO', 'fila-relatorio')  # fila de relatórios diários
RABBITMQ_USER = os.getenv('RABBITMQ_USER', 'guest')
RABBITMQ_PASS = os.getenv('RABBITMQ_PASS', 'guest')

BREVO_API_KEY = os.getenv('BREVO_API_KEY')
DESTINATARIO_EMAIL = os.getenv('DESTINATARIO_EMAIL')

# === LOGS ===
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

# === VARIÁVEIS DE CONTROLE ===
vendas_do_dia = {}
ultimo_funcionarios_para_notificar = []

# =========================================================
# === ENVIO DE E-MAIL INDIVIDUAL (FILA PRINCIPAL)
# =========================================================
def enviar_email_brevo(dados):
    """Envia o email via API Brevo (venda individual) para todos os funcionários da lista."""
    global ultimo_funcionarios_para_notificar

    url = "https://api.brevo.com/v3/smtp/email"

    saida = dados["saidaEstoque"]
    item = dados["itemEstoque"]
    lote = dados["lote"]
    funcionario = dados["funcionario"]

    funcionarios_para_notificar = dados.get("funcionariosParaNotificar", [])
    emails_destinatarios = [f["email"] for f in funcionarios_para_notificar if "email" in f]
    if not emails_destinatarios:
        emails_destinatarios = [DESTINATARIO_EMAIL]  # fallback

    # ✅ Guarda os funcionários da última venda (para uso no relatório diário)
    ultimo_funcionarios_para_notificar = funcionarios_para_notificar

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

    headers = {
        "accept": "application/json",
        "api-key": BREVO_API_KEY,
        "content-type": "application/json"
    }

    for email_destino in emails_destinatarios:
        payload = {
            "sender": {"name": "Sistema de Estoque", "email": "fernandoalmeida.mda@gmail.com"},
            "to": [{"email": email_destino}],
            "subject": assunto,
            "textContent": corpo_email.strip()
        }
        try:
            response = requests.post(url, json=payload, headers=headers)
            response.raise_for_status()
            logging.info(f"✅ E-mail individual enviado para {email_destino}")
        except Exception as e:
            logging.error(f"❌ Erro ao enviar e-mail para {email_destino}: {e}")

# =========================================================
# === CONSUMIDOR DA FILA DE RELATÓRIO (ACUMULA VENDAS)
# =========================================================
def callback_relatorio(ch, method, properties, body):
    """Acumula vendas da fila de relatório em memória."""
    global vendas_do_dia
    try:
        mensagem = json.loads(body.decode('utf-8'))
        item = mensagem["itemEstoque"]
        descricao = item["descricao"]
        preco = float(item.get("preco", 0))

        if descricao not in vendas_do_dia:
            vendas_do_dia[descricao] = {"quantidade": 1, "preco": preco}
        else:
            vendas_do_dia[descricao]["quantidade"] += 1

        logging.info(f"📦 Venda adicionada ao relatório: {descricao}")
    except Exception as e:
        logging.error(f"❌ Erro ao processar mensagem da fila de relatório: {e}")

# =========================================================
# === ENVIO DO RELATÓRIO DIÁRIO
# =========================================================
def enviar_relatorio_diario():
    """Envia o relatório consolidado por e-mail (ou mensagem de ausência de vendas)."""
    global vendas_do_dia, ultimo_funcionarios_para_notificar

    data_atual = datetime.now().strftime("%d/%m/%Y")
    url = "https://api.brevo.com/v3/smtp/email"
    headers = {
        "accept": "application/json",
        "api-key": BREVO_API_KEY,
        "content-type": "application/json"
    }

    # Determina destinatários (funcionários da última venda, se existirem)
    emails_destinatarios = [f["email"] for f in ultimo_funcionarios_para_notificar if "email" in f]
    if not emails_destinatarios:
        emails_destinatarios = [DESTINATARIO_EMAIL]

    if not vendas_do_dia:
        corpo = "Nenhuma venda foi registrada hoje."
        assunto = f"Relatório Diário de Vendas - {data_atual}"
    else:
        total_geral = sum(item["quantidade"] * item["preco"] for item in vendas_do_dia.values())
        corpo = f"RELATÓRIO DE VENDAS - {data_atual}\n\n"
        for descricao, info in vendas_do_dia.items():
            subtotal = info["quantidade"] * info["preco"]
            corpo += f"- {descricao}: {info['quantidade']} unidades vendidas, total R$ {subtotal:.2f}\n"
        corpo += f"\nTotal geral: R$ {total_geral:.2f}"
        assunto = f"Relatório Diário de Vendas - {data_atual}"

    for email_destino in emails_destinatarios:
        payload = {
            "sender": {"name": "Sistema de Estoque", "email": "fernandoalmeida.mda@gmail.com"},
            "to": [{"email": email_destino}],
            "subject": assunto,
            "textContent": corpo
        }
        try:
            response = requests.post(url, json=payload, headers=headers)
            response.raise_for_status()
            logging.info(f"📊 Relatório diário enviado para {email_destino}")
        except Exception as e:
            logging.error(f"❌ Erro ao enviar relatório diário: {e}")

    vendas_do_dia = {}  # limpa após envio

# =========================================================
# === AGENDAMENTO DO ENVIO DIÁRIO
# =========================================================
def agendar_relatorio_diario(hora_envio, minuto_envio):
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
# === CONSUMIDORES
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
# === CALLBACK PRINCIPAL (FILA DE VENDAS)
# =========================================================
def callback(ch, method, properties, body):
    """Processa mensagens de venda individual."""
    try:
        mensagem = json.loads(body.decode('utf-8'))
        logging.info(f"📩 Mensagem recebida: {mensagem}")
        enviar_email_brevo(mensagem)
    except Exception as e:
        logging.error(f"❌ Erro ao processar mensagem da fila principal: {e}")

# =========================================================
# === INICIALIZAÇÃO
# =========================================================
def iniciar_consumidor():
    """Inicia os consumidores e o agendamento do relatório diário."""
    try:
        threading.Thread(target=lambda: iniciar_consumidor_fila(RABBITMQ_QUEUE, callback), daemon=True).start()
        threading.Thread(target=lambda: iniciar_consumidor_fila(RABBITMQ_QUEUE_RELATORIO, callback_relatorio), daemon=True).start()
        agendar_relatorio_diario(18, 55)
        while True:
            time.sleep(1)
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
