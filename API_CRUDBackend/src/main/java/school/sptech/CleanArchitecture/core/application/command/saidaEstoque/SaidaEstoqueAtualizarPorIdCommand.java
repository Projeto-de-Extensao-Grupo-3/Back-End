package school.sptech.CleanArchitecture.core.application.command.saidaEstoque;

import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.entity.Parceiroaa;

import java.time.LocalDate;
import java.time.LocalTime;

public record SaidaEstoqueAtualizarPorIdCommand (
            Integer idSaidaEstoque,

            LocalDate data,

            LocalTime hora,

            Double qtdSaida,

            String motivoSaida,

            SaidaEstoqueFuncionarioCommand responsavel,
            SaidaEstoqueLoteItemEstoqueCommand loteItemEstoque,
            SaidaEstoqueCostureiraCommand costureira
){
}
