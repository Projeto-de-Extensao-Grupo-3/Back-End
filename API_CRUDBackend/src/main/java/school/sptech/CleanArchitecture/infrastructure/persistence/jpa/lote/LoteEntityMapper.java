package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote;

import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro.ParceiroEntity;

public class LoteEntityMapper {

    public static Lote ofEntity(LoteEntity entity) {
        Lote domain = new Lote();
        domain.setIdLote(entity.getIdLote());
        domain.setDescricao(entity.getDescricao());
        domain.setDataEntrada(entity.getDataEntrada());
        if (entity.getParceiro() != null) {
            Parceiro parceiro = new Parceiro();
            parceiro.setId(entity.getParceiro().getIdParceiro();
            domain.setParceiro(parceiro);
        }
        if (entity.getResponsavel() != null) {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(entity.getResponsavel().getIdFuncionario());
            domain.setResponsavel(funcionario);
        }
        return domain;
    }

    public static LoteEntity ofDomain(Lote domain) {
        ParceiroEntity parceiroEntity = new ParceiroEntity();
        parceiroEntity.setIdParceiro(domain.getParceiro().getId());
        parceiroEntity.setCategoria(domain.getParceiro().getCategoria());
        parceiroEntity.setNome(domain.getParceiro().getNome());
        parceiroEntity.setTelefone(domain.getParceiro().getTelefone());
        parceiroEntity.setEmail(domain.getParceiro().getEmail().getValue());
        parceiroEntity.setEndereco(domain.getParceiro().getEndereco());
        parceiroEntity.setIdentificacao(domain.getParceiro().getIdentificacao());

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setIdFuncionario(domain.getResponsavel().getIdFuncionario());
        funcionarioEntity.setNome(domain.getResponsavel().getNome());
        funcionarioEntity.setCpf(domain.getResponsavel().getCpf().getValue());
        funcionarioEntity.setTelefone(domain.getResponsavel().getTelefone().getValue());
        funcionarioEntity.setEmail(domain.getResponsavel().getEmail().getValue());
        funcionarioEntity.setSenha(domain.getResponsavel().getSenha());

        LoteEntity entity = new LoteEntity();
        entity.setIdLote(domain.getIdLote());
        entity.setDescricao(domain.getDescricao());
        entity.setDataEntrada(domain.getDataEntrada());
        entity.setParceiro(parceiroEntity);
        entity.setResponsavel(funcionarioEntity);
        return entity;
    }
}
