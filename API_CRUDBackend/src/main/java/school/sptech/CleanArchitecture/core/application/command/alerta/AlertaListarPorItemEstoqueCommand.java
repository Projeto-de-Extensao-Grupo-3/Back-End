package school.sptech.CleanArchitecture.core.application.command.alerta;

import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa.ConfeccaoRoupaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira.PrateleiraEntity;

import java.util.Set;

public class AlertaListarPorItemEstoqueCommand {
    private Integer idItemEstoque;
    private String descricao;
    private String complemento;
    private Double peso;
    private Double qtdMinimo;
    private Double qtdArmazenado;

    private CategoriaEntity categoria;

    private Set<CategoriaEntity> caracteristicas;

    private PrateleiraEntity prateleira;

    private Set<ConfeccaoRoupaEntity> confeccaoRoupa;
    private Double preco;

    private ImagemEntity imagem;
}
