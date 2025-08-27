package school.sptech.CleanArchitecture.core.application.dto.alerta;

import java.time.LocalDateTime;
public class AlertaCriacaoDto {

    private String descricao;
    private LocalDateTime dataHora;
    private AlertaItemEstoqueDto itemEstoque;

    public AlertaCriacaoDto() {
    }

    public AlertaCriacaoDto(String descricao, LocalDateTime dataHora, AlertaItemEstoqueDto itemEstoque) {
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.itemEstoque = itemEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public AlertaItemEstoqueDto getItemEstoque() {
        return itemEstoque;
    }

    public void setItemEstoque(AlertaItemEstoqueDto itemEstoque) {
        this.itemEstoque = itemEstoque;
    }
}
