package school.sptech.CleanArchitecture.core.application.mapper;


import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.SaidaEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

public class SaidaItemEstoqueMapper {

    public static SaidaEstoque ofCadastrarCommand(SaidaEstoqueCadastrarCommand command){
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(command.responsavel().id());
        LoteItemEstoque loteItemEstoque = new LoteItemEstoque();
        loteItemEstoque.setIdLoteItemEstoque(command.loteItemEstoque().id());
        Parceiro costureira = null;
        if (command.costureira() != null) {
            costureira = new Parceiro();
            costureira.setId(command.costureira().id());
        }
        return new SaidaEstoque(
                null,
                command.data(),
                command.hora(),
                command.qtdSaida(),
                command.motivoSaida(),
                funcionario,
                loteItemEstoque,
                costureira
        );
    }

}
