package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote;

import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

public class LoteEntityMapper {


    public static Lote ofEntity(LoteEntity entity) {
        Lote domain = new Lote();
        domain.setIdLote(entity.getIdLote());
        domain.setDescricao(entity.getDescricao());
        domain.setDataEntrada(entity.getDataEntrada());
        if (entity.getParceiro() != null) {
            Parceiro parceiro = new Parceiro();
            parceiro.setId(entity.getParceiro());
            domain.setParceiro(parceiro);
        }
        if (entity.getResponsavel() != null) {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(entity.getResponsavel());
            domain.setResponsavel(funcionario);
        }
        return domain;
    }

    public static LoteEntity ofDomain(Lote domain) {
        LoteEntity entity = new LoteEntity();
        entity.setIdLote(domain.getIdLote());
        entity.setDescricao(domain.getDescricao());
        entity.setDataEntrada(domain.getDataEntrada());
        entity.setParceiro(domain.getParceiro().getId());
        entity.setResponsavel(domain.getResponsavel().getIdFuncionario());
        return entity;
    }
}
