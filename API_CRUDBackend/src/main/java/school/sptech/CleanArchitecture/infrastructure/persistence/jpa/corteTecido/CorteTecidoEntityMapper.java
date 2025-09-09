package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.corteTecido;

import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntityMapper;

public class CorteTecidoEntityMapper {

    public static CorteTecido ofEntity(CorteTecidoEntity entity) {
        CorteTecido domain = new CorteTecido();
        domain.setIdCorteTecido(entity.getIdCorteTecido());
        domain.setInicio(entity.getInicio());
        domain.setTermino(entity.getTermino());
        domain.setFuncionario(FuncionarioEntityMapper.ofEntity(entity.getFuncionario()));
        domain.setLoteItemEstoque(LoteItemEstoqueEntityMapper.ofEntity(entity.getLoteItemEstoque()));
        return domain;
    }

    public static CorteTecidoEntity ofDomain(CorteTecido domain) {
        CorteTecidoEntity entity = new CorteTecidoEntity();
        entity.setIdCorteTecido(domain.getIdCorteTecido());
        entity.setInicio(domain.getInicio());
        entity.setTermino(domain.getTermino());
        entity.setFuncionario(FuncionarioEntityMapper.ofDomain(domain.getFuncionario()));
        entity.setLoteItemEstoque(LoteItemEstoqueEntityMapper.ofDomain(domain.getLoteItemEstoque()));
        return entity;
    }
}
