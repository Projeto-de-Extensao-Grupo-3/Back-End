package school.sptech.CleanArchitecture.core.domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class SaidaEstoque {

    private Integer idSaidaEstoque;
    private LocalDate data;
    private LocalTime hora;
    private Double qtdSaida;
    private String motivoSaida;

    private Funcionario responsavel;

    private LoteItemEstoque loteItemEstoque;

    private Parceiro costureira;

    public SaidaEstoque() {
    }

    public SaidaEstoque(Integer idSaidaEstoque, LocalDate data, LocalTime hora, Double qtdSaida, String motivoSaida, Funcionario responsavel, LoteItemEstoque loteItemEstoque, Parceiro costureira) {
        this.idSaidaEstoque = idSaidaEstoque;
        this.data = data;
        this.hora = hora;
        this.qtdSaida = qtdSaida;
        this.motivoSaida = motivoSaida;
        this.responsavel = responsavel;
        this.loteItemEstoque = loteItemEstoque;
        this.costureira = costureira;
    }

    public SaidaEstoque(LocalDate data, LocalTime hora, Double qtdSaida, String motivoSaida, Funcionario responsavel, LoteItemEstoque loteItemEstoque, Parceiro costureira) {
        this.data = data;
        this.hora = hora;
        this.qtdSaida = qtdSaida;
        this.motivoSaida = motivoSaida;
        this.responsavel = responsavel;
        this.loteItemEstoque = loteItemEstoque;
        this.costureira = costureira;
    }

    public Integer getIdSaidaEstoque() {
        return idSaidaEstoque;
    }

    public void setIdSaidaEstoque(Integer idSaidaEstoque) {
        this.idSaidaEstoque = idSaidaEstoque;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Double getQtdSaida() {
        return qtdSaida;
    }

    public void setQtdSaida(Double qtdSaida) {
        this.qtdSaida = qtdSaida;
    }

    public String getMotivoSaida() {
        return motivoSaida;
    }

    public void setMotivoSaida(String motivoSaida) {
        this.motivoSaida = motivoSaida;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public LoteItemEstoque getLoteItemEstoque() {
        return loteItemEstoque;
    }

    public void setLoteItemEstoque(LoteItemEstoque loteItemEstoque) {
        this.loteItemEstoque = loteItemEstoque;
    }

    public Parceiro getCostureira() {
        return costureira;
    }

    public void setCostureira(Parceiro costureira) {
        this.costureira = costureira;
    }

    @Override
    public String toString() {
        return "SaidaEstoque{" +
                "idSaidaEstoque=" + idSaidaEstoque +
                ", data=" + data +
                ", hora=" + hora +
                ", qtdSaida=" + qtdSaida +
                ", motivoSaida='" + motivoSaida + '\'' +
                ", responsavel=" + responsavel +
                ", loteItemEstoque=" + loteItemEstoque +
                ", costureira=" + costureira +
                '}';
    }
}
