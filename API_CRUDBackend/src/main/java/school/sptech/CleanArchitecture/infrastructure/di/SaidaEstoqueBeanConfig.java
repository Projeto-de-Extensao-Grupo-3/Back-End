package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.BuscarFuncionarioPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.FuncionarioListarPorPermissaoUserCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueAtualizarPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueAtualizarQuantidadeUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueBuscarPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueCadastrarItemUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque.BuscarPorIdLoteItemEstoqueUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.parceiro.BuscarParceiroPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueAdapter;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.saidaEstoque.SaidaEstoqueAdapter;
import school.sptech.CleanArchitecture.infrastructure.web.rabbitmq.RabbitProducer;

import java.util.List;

@Configuration
public class SaidaEstoqueBeanConfig {

    @Bean
    public SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase saidaEstoqueAtualizarQuantidadeLoteDeItemUseCase(LoteItemEstoqueAdapter adapter, ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarPorIdUseCase){
        return new SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase(adapter, itemEstoqueBuscarPorIdUseCase);
    }

    @Bean
    public SaidaEstoqueAtualizarPorIdUseCase saidaEstoqueAtualizarPorIdCommand(SaidaEstoqueAdapter adapter, SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarQuantidadeUseCase, BuscarParceiroPorIdUseCase parceiroPorIdUseCase, BuscarFuncionarioPorIdUseCase funcionarioPorIdUseCase,
                                                                               BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase, ItemEstoqueAtualizarPorIdUseCase itemEstoqueAtualizarPorIdUseCase){
        return new SaidaEstoqueAtualizarPorIdUseCase(adapter, atualizarQuantidadeUseCase, parceiroPorIdUseCase, funcionarioPorIdUseCase, loteItemEstoqueUseCase, itemEstoqueAtualizarPorIdUseCase);
    }

    @Bean
    public SaidaEstoqueBuscarPorDataUseCase saidaEstoqueBuscarPorDataUseCase(SaidaEstoqueAdapter adapter){
        return new SaidaEstoqueBuscarPorDataUseCase(adapter);
    }

    @Bean
    public SaidaEstoqueBuscarPorIdUseCase saidaEstoqueBuscarPorIdUseCase(SaidaEstoqueAdapter adapter){
        return new SaidaEstoqueBuscarPorIdUseCase(adapter);
    }

    @Bean
    public SaidaEstoqueBuscarPorMotivoUseCase saidaEstoqueBuscarPorMotivoUseCase(SaidaEstoqueAdapter adapter){
        return new SaidaEstoqueBuscarPorMotivoUseCase(adapter);
    }

    @Bean SaidaEstoqueEnviarEmailENotificarObservers enviarEmailENotificarObservers(RabbitProducer rabbitProducer, SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarSaidaUseCase, FuncionarioListarPorPermissaoUserCase listarPorPermissaoUserCase){
        return new SaidaEstoqueEnviarEmailENotificarObservers(rabbitProducer, atualizarSaidaUseCase, listarPorPermissaoUserCase);
    }

    @Bean
    public SaidaEstoqueCadastrarUseCase saidaEstoqueCadastrarUseCase(SaidaEstoqueAdapter adapter, ItemEstoqueAtualizarPorIdUseCase atualizarPorIdUseCase, ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarUseCase, BuscarFuncionarioPorIdUseCase funcionarioPorIdUseCase, BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase,
                                                                     BuscarParceiroPorIdUseCase parceiroPorIdUseCase, SaidaEstoqueEnviarEmailENotificarObservers enviarEmailENotificarObservers){

        return new SaidaEstoqueCadastrarUseCase(adapter, atualizarPorIdUseCase, itemEstoqueBuscarUseCase, funcionarioPorIdUseCase, loteItemEstoqueUseCase, parceiroPorIdUseCase, enviarEmailENotificarObservers);
    }

    @Bean
    public SaidaEstoqueListAllUseCAse saidaEstoqueListAllUseCAse(SaidaEstoqueAdapter adapter){
        return new SaidaEstoqueListAllUseCAse(adapter);
    }

    @Bean
    public SaidaEstoqueRemoverPorIdUseCase saidaEstoqueRemoverPorIdUseCase(SaidaEstoqueAdapter adapter){
        return new SaidaEstoqueRemoverPorIdUseCase(adapter);
    }
}
