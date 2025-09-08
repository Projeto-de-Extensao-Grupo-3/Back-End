package school.sptech.CleanArchitecture.core.application.command.saidaEstoque;

import java.time.LocalDate;
import java.time.LocalTime;

public record SaidaEstoqueCadastrarCommand(
      LocalDate data,
      LocalTime hora,
    Double qtdSaida,
    String motivoSaida,
    SaidaEstoqueFuncionarioCommand responsavel,
    SaidaEstoqueLoteItemEstoqueCommand loteItemEstoque,
    SaidaEstoqueCostureiraCommand costureira,
      Double qtdAtualizar
) {
}
