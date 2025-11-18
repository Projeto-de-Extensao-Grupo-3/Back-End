package school.sptech.CleanArchitecture.core.domain.entity;

public class CorteTecido {
    private Integer idCorteTecido;
    private String inicio;
    private String termino;
    private Funcionario funcionario;
    private LoteItemEstoque loteItemEstoque;

    public Integer getIdCorteTecido() {
        return idCorteTecido;
    }

    public void setIdCorteTecido(Integer idCorteTecido) {
        this.idCorteTecido = idCorteTecido;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LoteItemEstoque getLoteItemEstoque() {
        return loteItemEstoque;
    }

    public void setLoteItemEstoque(LoteItemEstoque loteItemEstoque) {
        this.loteItemEstoque = loteItemEstoque;
    }
}
