package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.application.usecase.funcionario.FuncionarioListarPorPermissaoUserCase;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Permissao;
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

    private final FuncionarioListarPorPermissaoUserCase listarPorPermissaoUserCase;

    private static final int PERMISSAO_PARA_RECEBER_NOTIFICACAO = 7;

    private final List<Observer> observadores = new ArrayList<>();

    public SaidaEstoqueEnviarEmailENotificarObservers(RabbitProducer rabbitProducer, SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarSaidaUseCase, FuncionarioListarPorPermissaoUserCase listarPorPermissaoUserCase) {
        this.rabbitProducer = rabbitProducer;
        this.atualizarSaidaUseCase = atualizarSaidaUseCase;
        this.listarPorPermissaoUserCase = listarPorPermissaoUserCase;
    }

    public void execute(SaidaEstoque saidaDeEstoque){
        ItemEstoque itemEstoqueAtualizado = atualizarSaidaUseCase.execute(saidaDeEstoque, 0.0);
        System.out.println("Enviando para Rabbit " + itemEstoqueAtualizado.getQtdArmazenado());
        notificarObservers(itemEstoqueAtualizado);

        List<Funcionario> funcionariosParaNotificar = listarPorPermissaoUserCase.execute(new Permissao(PERMISSAO_PARA_RECEBER_NOTIFICACAO));
        EmailDto emailDto = new EmailDto(saidaDeEstoque, itemEstoqueAtualizado, funcionariosParaNotificar);
        try {
            rabbitProducer.enviarItemParaRelatorio(emailDto);
        }catch (Exception e){
            System.out.println("❌ Falha ao enviar objeto para o relatório.");
        }
        if (itemEstoqueAtualizado.getNotificar()) {
            try {
                rabbitProducer.enviarSaidaMarcada(emailDto);
            }catch (Exception e){
                System.out.println("❌ Falha ao enviar objeto marcado para fila RabbitMQ.");
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
