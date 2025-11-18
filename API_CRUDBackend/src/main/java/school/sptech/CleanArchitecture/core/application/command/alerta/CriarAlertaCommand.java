package school.sptech.CleanArchitecture.core.application.command.alerta;

import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.time.LocalDateTime;
public class CriarAlertaCommand {

    private String descricao;
    private LocalDateTime dataHora;
    private ItemEstoque itemEstoque;

    public CriarAlertaCommand() {
    }

    public CriarAlertaCommand(String descricao, LocalDateTime dataHora, ItemEstoque itemEstoque) {
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

    public ItemEstoque getItemEstoque() {
        return itemEstoque;
    }

    public void setItemEstoque(ItemEstoque itemEstoque) {
        this.itemEstoque = itemEstoque;
    }
}
