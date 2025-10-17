package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;
import school.sptech.CleanArchitecture.core.domain.observer.Subject;
import school.sptech.CleanArchitecture.infrastructure.web.rabbitmq.EmailDto;
import school.sptech.CleanArchitecture.infrastructure.web.rabbitmq.RabbitProducer;

import java.util.ArrayList;
import java.util.List;

public class SaidaEstoqueEnviarEmailENotificarObservers implements Subject {

    private final RabbitProducer rabbitProducer;

    private final SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarSaidaUseCase;

    private final List<Observer> observadores = new ArrayList<>();

    public SaidaEstoqueEnviarEmailENotificarObservers(RabbitProducer rabbitProducer, SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarSaidaUseCase) {
        this.rabbitProducer = rabbitProducer;
        this.atualizarSaidaUseCase = atualizarSaidaUseCase;
    }

    public void execute(SaidaEstoque saidaDeEstoque){
        ItemEstoque itemEstoqueAtualizado = atualizarSaidaUseCase.execute(saidaDeEstoque, 0.0);
        notificarObservers(itemEstoqueAtualizado);

        if (itemEstoqueAtualizado.getNotificar()) {
            EmailDto emailDto = new EmailDto(saidaDeEstoque, itemEstoqueAtualizado);
            try {
                rabbitProducer.enviarPedido(emailDto);
            }catch (Exception e){
                System.out.println("❌ Falha ao enviar objeto para fila RabbitMQ");
            }
        }
    }

    @Override
    public void adicionarObservador(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(Observer observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservers(ItemEstoque itemEstoque) {
        System.out.println("NOTIFICANDO OBSERVERSSSS");

        for (Observer observador : observadores) {
            observador.atualizarQuantidade(itemEstoque);
        }
    }

}
