import pika
import json
import requests
import logging
import os
from dotenv import load_dotenv

# === CARREGA VARIÁVEIS DO ARQUIVO .env (SE EXISTIR) ===
load_dotenv()

# === CONFIGURAÇÕES ===
RABBITMQ_HOST = os.getenv('RABBITMQ_HOST', 'localhost')
RABBITMQ_QUEUE = os.getenv('RABBITMQ_QUEUE', 'fila-emails')
RABBITMQ_USER = os.getenv('RABBITMQ_USER', 'guest')
RABBITMQ_PASS = os.getenv('RABBITMQ_PASS', 'guest')

BREVO_API_KEY = os.getenv('BREVO_API_KEY')
DESTINATARIO_EMAIL = os.getenv('DESTINATARIO_EMAIL')

# === LOGS ===
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')


def enviar_email_brevo(dados):
    """Envia o email via API Brevo."""
    url = "https://api.brevo.com/v3/smtp/email"

    saida = dados["saidaEstoque"]
    item = dados["itemEstoque"]
    lote = dados["lote"]
    funcionario = dados["funcionario"]

    # 👉 Ajusta horário caso venha como lista [2025, 10, 1, 19, 55, 30]
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
        logging.info(f"✅ E-mail enviado com sucesso para {DESTINATARIO_EMAIL}")
    except Exception as e:
        logging.error(f"❌ Erro ao enviar e-mail: {e}")



def callback(ch, method, properties, body):
    """Função chamada quando uma nova mensagem chega na fila."""
    try:
        mensagem = json.loads(body.decode('utf-8'))
        logging.info(f"📩 Mensagem recebida: {mensagem}")
        enviar_email_brevo(mensagem)
    except Exception as e:
        logging.error(f"❌ Erro ao processar mensagem: {e}")


def iniciar_consumidor():
    """Conecta ao RabbitMQ e inicia o consumo da fila."""
    connection = None
    try:
        credentials = pika.PlainCredentials(RABBITMQ_USER, RABBITMQ_PASS)
        connection = pika.BlockingConnection(
            pika.ConnectionParameters(
                host=RABBITMQ_HOST,
                port=5672,
                credentials=credentials
            )
        )

        channel = connection.channel()
        channel.queue_declare(queue=RABBITMQ_QUEUE, durable=True)

        channel.basic_consume(
            queue=RABBITMQ_QUEUE,
            on_message_callback=callback,
            auto_ack=True
        )

        print(f"✅ Conectado ao RabbitMQ ({RABBITMQ_HOST}) - Aguardando mensagens da aplicação Java...")

        try:
            channel.start_consuming()
        except KeyboardInterrupt:
            print("\n🛑 Interrompido manualmente. Encerrando consumidor com segurança...")
            channel.stop_consuming()

    except Exception as e:
        print(f"❌ Erro RabbitMQ: {e}")
        print("⚠️ Verifique se o RabbitMQ está rodando e se as credenciais estão corretas.")
    finally:
        if connection and not connection.is_closed:
            connection.close()
            print("🔌 Conexão RabbitMQ fechada.")

if __name__ == "__main__":
    if not BREVO_API_KEY or not DESTINATARIO_EMAIL:
        print("❌ Faltando variáveis de ambiente: BREVO_API_KEY e/ou DESTINATARIO_EMAIL.")
        exit(1)

    iniciar_consumidor()
