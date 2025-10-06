package school.sptech.CleanArchitecture.core.domain.entity;

public class Prateleira {

    private Integer idPrateleira;
    private String codigo;

    public Prateleira() {
    }

    public Prateleira(Integer idPrateleira) {
        this.idPrateleira = idPrateleira;
    }

    public Integer getIdPrateleira() {
        return idPrateleira;
    }

    public void setIdPrateleira(Integer idPrateleira) {
        this.idPrateleira = idPrateleira;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
