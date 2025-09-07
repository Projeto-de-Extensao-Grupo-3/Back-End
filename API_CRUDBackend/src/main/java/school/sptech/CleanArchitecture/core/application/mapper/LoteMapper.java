package school.sptech.CleanArchitecture.core.application.mapper;

import school.sptech.CleanArchitecture.core.application.command.lote.CriarLoteCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

public class LoteMapper {

    public static Lote ofCriarLoteCommand(CriarLoteCommand command) {
        Lote lote = new Lote();
        lote.setDescricao(command.descricao());
        lote.setDataEntrada(command.dataEntrada());

        // Criar parceiro com apenas o ID
        if (command.parceiroId() != null) {
            Parceiro parceiro = new Parceiro();
            parceiro.setId(command.parceiroId());
            lote.setParceiro(parceiro);
        }

        // Criar responsável com apenas o ID
        if (command.responsavelId() != null) {
            Funcionario responsavel = new Funcionario();
            responsavel.setIdFuncionario(command.responsavelId());
            lote.setResponsavel(responsavel);
        }

        return lote;
    }
}
