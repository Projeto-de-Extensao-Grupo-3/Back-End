package school.sptech.CleanArchitecture.config.rabbitmq;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RabbitMQConnectionChecker implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConnectionChecker.class);
    private final ConnectionFactory connectionFactory;

    public RabbitMQConnectionChecker(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void run(String... args) {
        try {
            logger.info("🔌 Tentando conectar ao RabbitMQ...");
            connectionFactory.createConnection();
            logger.info("✅ Conexão com RabbitMQ estabelecida com sucesso!");
        } catch (Exception e) {
            logger.error("❌ Falha ao conectar ao RabbitMQ: {}", e.getMessage());
        }
    }
}
