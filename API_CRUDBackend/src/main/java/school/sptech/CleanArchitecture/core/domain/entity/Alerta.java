package school.sptech.CleanArchitecture.core.domain.entity;

import java.time.LocalDateTime;

public class Alerta {

    private Integer idAlerta;
    private String descricao;
    private LocalDateTime dataHora;
    private ItemEstoque itemEstoque;

    public Alerta() {
    }

    public Alerta(Integer idAlerta, String descricao, LocalDateTime dataHora, ItemEstoque itemEstoque) {
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

    public ItemEstoque getItemEstoque() {
        return itemEstoque;
    }

    public void setItemEstoque(ItemEstoque itemEstoque) {
        this.itemEstoque = itemEstoque;
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "idAlerta=" + idAlerta +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                ", itemEstoque=" + itemEstoque +
                '}';
    }
}
