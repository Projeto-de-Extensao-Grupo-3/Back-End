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

    public void enviarPedido(EmailDto emailDto) {
        rabbitTemplate.convertAndSend(
                RabbitTemplateConfiguration.EMAIL_EXCHANGE,
                RabbitTemplateConfiguration.ROUTING_KEY_SAIDA,
                emailDto
        );
        System.out.println("📦 Aviso de venda enviado: " + emailDto);
    }
}
