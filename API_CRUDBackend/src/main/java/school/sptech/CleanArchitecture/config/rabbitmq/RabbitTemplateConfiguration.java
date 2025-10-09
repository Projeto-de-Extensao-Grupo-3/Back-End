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
    public static final String QUEUE_EMAIL_SAIDA = "fila_email_saida_estoque";
    public static final String QUEUE_EMAIL_ESTOQUE = "fila_email_estoque";
    public static final String QUEUE_EMAIL_TODOS = "fila_email_todos";

    // Routing keys
    public static final String ROUTING_KEY_SAIDA = "email.saida";
    public static final String ROUTING_KEY_ESTOQUE = "email.estoque.*";
    public static final String ROUTING_KEY_ALL = "email.#";

    // Exchange do tipo Topic
    @Bean
    public TopicExchange emailExchange() {
        return new TopicExchange(EMAIL_EXCHANGE);
    }

    // Filas
    @Bean
    public Queue queueEmailVenda() {
        return new Queue(QUEUE_EMAIL_SAIDA, true);
    }

    @Bean
    public Queue queueEmailEstoque() {
        return new Queue(QUEUE_EMAIL_ESTOQUE, true);
    }

    @Bean
    public Queue queueEmailTodos() {
        return new Queue(QUEUE_EMAIL_TODOS, true);
    }

    // Bindings
    @Bean
    public Binding bindingEmailVenda(Queue queueEmailVenda, TopicExchange emailExchange) {
        return BindingBuilder.bind(queueEmailVenda).to(emailExchange).with(ROUTING_KEY_SAIDA);
    }

    @Bean
    public Binding bindingEmailEstoque(Queue queueEmailEstoque, TopicExchange emailExchange) {
        return BindingBuilder.bind(queueEmailEstoque).to(emailExchange).with(ROUTING_KEY_ESTOQUE);
    }

    @Bean
    public Binding bindingEmailTodos(Queue queueEmailTodos, TopicExchange emailExchange) {
        return BindingBuilder.bind(queueEmailTodos).to(emailExchange).with(ROUTING_KEY_ALL);
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