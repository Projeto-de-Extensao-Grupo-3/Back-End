package school.sptech.CleanArchitecture.core.application.dto.alerta;

import java.time.LocalDateTime;

public class AlertaResponseDto {
    private Integer idAlerta;
    private String descricao;
    private LocalDateTime dataHora;
    private AlertaItemEstoqueDto itemEstoque;

    public AlertaResponseDto() {
    }

    public AlertaResponseDto(Integer idAlerta, String descricao, LocalDateTime dataHora, AlertaItemEstoqueDto itemEstoque) {
        this.idAlerta = idAlerta;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.itemEstoque = itemEstoque;
    }

    public Integer getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Integer idAlerta) {
        this.idAlerta = idAlerta;
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
