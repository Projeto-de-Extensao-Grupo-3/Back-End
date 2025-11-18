package school.sptech.CleanArchitecture.infrastructure.web.rabbitmq;

import lombok.Data;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

import java.time.LocalDateTime;

@Data
public class SaidaEstoqueEmailDto {
    private LocalDateTime horarioSaida;
    private Double qtdSaida;
    private String motivoSaida;

    public SaidaEstoqueEmailDto(SaidaEstoque saida) {
        if (saida.getData() != null && saida.getHora() != null) {
            this.horarioSaida = LocalDateTime.of(saida.getData(), saida.getHora());
        }
        this.qtdSaida = saida.getQtdSaida();
        this.motivoSaida = saida.getMotivoSaida();
    }
}
