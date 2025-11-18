package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.corteTecido;

import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.TelefoneVo;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntity;

public class CorteTecidoEntityMapper {

    public static CorteTecido ofEntity(CorteTecidoEntity entity) {
        CorteTecido domain = new CorteTecido();
        domain.setIdCorteTecido(entity.getIdCorteTecido());
        domain.setInicio(entity.getInicio());
        domain.setTermino(entity.getTermino());

        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(entity.getFuncionario().getIdFuncionario());
        funcionario.setNome(entity.getFuncionario().getNome());
        EmailVo email = new EmailVo(entity.getFuncionario().getEmail());
        funcionario.setEmail(email);
        TelefoneVo telefoneVo = new TelefoneVo(entity.getFuncionario().getTelefone());
        funcionario.setTelefone(telefoneVo);

        domain.setFuncionario(funcionario);

        LoteItemEstoque loteItemEstoque = new LoteItemEstoque();
        loteItemEstoque.setIdLoteItemEstoque(entity.getLoteItemEstoque().getIdLoteItemEstoque());
        domain.setLoteItemEstoque(loteItemEstoque);
        return domain;
    }

    public static CorteTecido ofEntityCadastrar(CorteTecidoEntity entity) {
        CorteTecido domain = new CorteTecido();
        domain.setIdCorteTecido(entity.getIdCorteTecido());
        domain.setInicio(entity.getInicio());
        domain.setTermino(entity.getTermino());

        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(entity.getFuncionario().getIdFuncionario());
        domain.setFuncionario(funcionario);

        LoteItemEstoque loteItemEstoque = new LoteItemEstoque();
        loteItemEstoque.setIdLoteItemEstoque(entity.getLoteItemEstoque().getIdLoteItemEstoque());
        domain.setLoteItemEstoque(loteItemEstoque);
        return domain;
    }

    public static CorteTecidoEntity ofDomain(CorteTecido domain) {
        CorteTecidoEntity entity = new CorteTecidoEntity();
        entity.setIdCorteTecido(domain.getIdCorteTecido());
        entity.setInicio(domain.getInicio());
        entity.setTermino(domain.getTermino());

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setIdFuncionario(domain.getFuncionario().getIdFuncionario());
        funcionarioEntity.setNome(domain.getFuncionario().getNome());
        funcionarioEntity.setCpf(domain.getFuncionario().getCpf().getValue());
        funcionarioEntity.setTelefone(domain.getFuncionario().getTelefone().getValue());
        funcionarioEntity.setEmail(domain.getFuncionario().getEmail().getValue());
        entity.setFuncionario(funcionarioEntity);

        LoteItemEstoqueEntity loteItemEstoqueEntity = new LoteItemEstoqueEntity();
        loteItemEstoqueEntity.setIdLoteItemEstoque(domain.getLoteItemEstoque().getIdLoteItemEstoque());
        loteItemEstoqueEntity.setQtdItem(domain.getLoteItemEstoque().getQtdItem());
        loteItemEstoqueEntity.setPreco(domain.getLoteItemEstoque().getPreco());
        loteItemEstoqueEntity.setItemEstoque(ItemEstoqueEntityMapper.ofDomain(domain.getLoteItemEstoque().getItemEstoque()));
        loteItemEstoqueEntity.setLote(LoteEntityMapper.ofDomain(domain.getLoteItemEstoque().getLote()));
        entity.setLoteItemEstoque(loteItemEstoqueEntity);
        return entity;
    }

    public static CorteTecidoEntity ofDomainCadsatrar(CorteTecido domain) {
        CorteTecidoEntity entity = new CorteTecidoEntity();
        entity.setIdCorteTecido(domain.getIdCorteTecido());
        entity.setInicio(domain.getInicio());
        entity.setTermino(domain.getTermino());

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setIdFuncionario(domain.getFuncionario().getIdFuncionario());
        entity.setFuncionario(funcionarioEntity);

        LoteItemEstoqueEntity loteItemEstoqueEntity = new LoteItemEstoqueEntity();
        loteItemEstoqueEntity.setIdLoteItemEstoque(domain.getLoteItemEstoque().getIdLoteItemEstoque());
        entity.setLoteItemEstoque(loteItemEstoqueEntity);
        return entity;
    }

    public static CorteTecidoEntity ofDomainListarTodos(CorteTecido domain) {
        CorteTecidoEntity entity = new CorteTecidoEntity();
        entity.setIdCorteTecido(domain.getIdCorteTecido());
        entity.setInicio(domain.getInicio());
        entity.setTermino(domain.getTermino());

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setIdFuncionario(domain.getFuncionario().getIdFuncionario());
        funcionarioEntity.setNome(domain.getFuncionario().getNome());
        funcionarioEntity.setTelefone(domain.getFuncionario().getTelefone().getValue());
        funcionarioEntity.setEmail(domain.getFuncionario().getEmail().getValue());

        entity.setFuncionario(funcionarioEntity);
        return entity;
    }
}
