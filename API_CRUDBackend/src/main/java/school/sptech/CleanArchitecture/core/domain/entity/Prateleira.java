package school.sptech.CleanArchitecture.core.domain.entity;

public class Prateleira {

    private Integer idPrateleira;
    private String codigo;

    public Prateleira() {
    }

    public Prateleira(Integer idPrateleira) {
        this.idPrateleira = idPrateleira;
    }

    public Prateleira(Integer idPrateleira, String codigo) {
        this.idPrateleira = idPrateleira;
        this.codigo = codigo;
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

    @Override
    public String toString() {
        return "Prateleira{" +
                "idPrateleira=" + idPrateleira +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
