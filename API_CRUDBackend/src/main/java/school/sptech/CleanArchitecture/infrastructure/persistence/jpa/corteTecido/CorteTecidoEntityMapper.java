package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.corteTecido;

import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntityMapper;

public class CorteTecidoEntityMapper {

    public static CorteTecido ofEntity(CorteTecidoEntity entity) {
        CorteTecido domain = new CorteTecido();
        domain.setIdCorteTecido(entity.getIdCorteTecido());
        domain.setInicio(entity.getInicio());
        domain.setTermino(entity.getTermino());

        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(entity.getFuncionario());
        domain.setFuncionario(funcionario);

        LoteItemEstoque loteItemEstoque = new LoteItemEstoque();
        loteItemEstoque.setIdLoteItemEstoque(entity.getLoteItemEstoque());
        domain.setLoteItemEstoque(loteItemEstoque);
        return domain;
    }

    public static CorteTecidoEntity ofDomain(CorteTecido domain) {
        CorteTecidoEntity entity = new CorteTecidoEntity();
        entity.setIdCorteTecido(domain.getIdCorteTecido());
        entity.setInicio(domain.getInicio());
        entity.setTermino(domain.getTermino());
        entity.setFuncionario(domain.getFuncionario().getIdFuncionario());
        entity.setLoteItemEstoque(domain.getLoteItemEstoque().getIdLoteItemEstoque());
        return entity;
    }
}
