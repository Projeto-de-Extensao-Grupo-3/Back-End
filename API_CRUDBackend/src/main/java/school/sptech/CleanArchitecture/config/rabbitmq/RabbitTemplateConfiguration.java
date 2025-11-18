package school.sptech.CleanArchitecture.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitTemplateConfiguration {
    // Nome da exchange
    public static final String EMAIL_EXCHANGE = "email_exchange";

    // Nomes das filas
    public static final String QUEUE_EMAIL_SAIDA = "fila_email_saida";
    public static final String QUEUE_EMAIL_RELATORIO = "fila_email_relatorio";

    // Routing keys
    public static final String ROUTING_KEY_SAIDA = "email.saida";
    public static final String ROUTING_KEY_RELATORIO = "email.relatorio";

    // Exchange do tipo Topic
    @Bean
    public TopicExchange emailExchange() {
        return new TopicExchange(EMAIL_EXCHANGE);
    }

    // Filas
    @Bean
    public Queue queueEmailSaida() {
        return new Queue(QUEUE_EMAIL_SAIDA, true);
    }

    @Bean
    public Queue queueEmailRelatorio() {
        return new Queue(QUEUE_EMAIL_RELATORIO, true);
    }

    // Bindings
    @Bean
    public Binding bindingEmailVenda(Queue queueEmailSaida, TopicExchange emailExchange) {
        return BindingBuilder.bind(queueEmailSaida).to(emailExchange).with(ROUTING_KEY_SAIDA);
    }

    @Bean
    public Binding bindingEmailEstoque(Queue queueEmailRelatorio, TopicExchange emailExchange) {
        return BindingBuilder.bind(queueEmailRelatorio).to(emailExchange).with(ROUTING_KEY_RELATORIO);
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}