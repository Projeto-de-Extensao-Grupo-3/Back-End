package school.sptech.CleanArchitecture.infrastructure.web.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.config.rabbitmq.RabbitTemplateConfiguration;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.saidaEstoque.SaidaEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque.SaidaEstoqueResponseDto;

@Service
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

<<<<<<< HEAD
    public void enviarPedido(EmailDto emailDto) {
        rabbitTemplate.convertAndSend(
                RabbitTemplateConfiguration.EMAIL_EXCHANGE,
                RabbitTemplateConfiguration.ROUTING_KEY_SAIDA,
                emailDto
        );
        System.out.println("📦 Aviso de venda enviado: " + emailDto);
=======
    public void enviarPedido(SaidaEstoqueResponseDto pedido) {
        rabbitTemplate.convertAndSend(
                RabbitTemplateConfiguration.EMAIL_EXCHANGE,
                RabbitTemplateConfiguration.ROUTING_KEY_SAIDA,
                pedido
        );
        System.out.println("📦 Aviso de venda enviado: " + pedido);
>>>>>>> cd5bdb80eb00d9ef103a99568fe7732d716c0e92
    }
}
