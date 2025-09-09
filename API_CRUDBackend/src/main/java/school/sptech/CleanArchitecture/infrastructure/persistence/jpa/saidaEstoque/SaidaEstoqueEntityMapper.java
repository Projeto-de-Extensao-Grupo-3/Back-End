package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.saidaEstoque;


import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro.ParceiroEntity;

public class SaidaEstoqueEntityMapper {
    public static SaidaEstoqueEntity toEntity(SaidaEstoque saidaEstoque) {

        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setIdFuncionario(saidaEstoque.getResponsavel().getIdFuncionario());

        LoteItemEstoqueEntity loteItemEstoque = new LoteItemEstoqueEntity();
        loteItemEstoque.setIdLoteItemEstoque(saidaEstoque.getLoteItemEstoque().getIdLoteItemEstoque());

        ParceiroEntity costureira = null;
        if (saidaEstoque.getCostureira() != null) {
            costureira = new ParceiroEntity();
            costureira.setIdParceiro(saidaEstoque.getCostureira().getId());
        }
        return new SaidaEstoqueEntity(
                null,
                saidaEstoque.getData(),
                saidaEstoque.getHora(),
                saidaEstoque.getQtdSaida(),
                saidaEstoque.getMotivoSaida(),
                funcionario,
                loteItemEstoque,
                costureira
        );

    }

    public static SaidaEstoque toDomain(SaidaEstoqueEntity saidaEstoque) {

        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(saidaEstoque.getResponsavel().getIdFuncionario());

        LoteItemEstoque loteItemEstoque = new LoteItemEstoque();
        loteItemEstoque.setIdLoteItemEstoque(saidaEstoque.getLoteItemEstoque().getIdLoteItemEstoque());

        Parceiro costureira = null;
        if (saidaEstoque.getCostureira() != null) {
            costureira = new Parceiro();
            costureira.setId(saidaEstoque.getCostureira().getIdParceiro());
        }
        return new SaidaEstoque(
                null,
                saidaEstoque.getData(),
                saidaEstoque.getHora(),
                saidaEstoque.getQtdSaida(),
                saidaEstoque.getMotivoSaida(),
                funcionario,
                loteItemEstoque,
                costureira
        );

    }
}
