package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote;

import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;
import school.sptech.CleanArchitecture.core.domain.valueObject.CpfVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.TelefoneVo;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro.ParceiroEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.lote.LoteEmEstoqueDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.lote.LoteEmEstoqueResponse;

public class LoteEntityMapper {

    public static Lote ofEntity(LoteEntity entity) {
        Lote domain = new Lote();
        domain.setIdLote(entity.getIdLote());
        domain.setDescricao(entity.getDescricao());
        domain.setDataEntrada(entity.getDataEntrada());
        if (entity.getParceiro() != null) {
            Parceiro parceiro = new Parceiro();
            parceiro.setId(entity.getParceiro().getIdParceiro());
            parceiro.setCategoria(entity.getParceiro().getCategoria());
            parceiro.setNome(entity.getParceiro().getNome());
            parceiro.setTelefone(entity.getParceiro().getTelefone());
            EmailVo emailVo = new EmailVo(entity.getParceiro().getEmail());
            parceiro.setEmail(emailVo);
            parceiro.setEndereco(entity.getParceiro().getEndereco());
            parceiro.setIdentificacao(entity.getParceiro().getIdentificacao());
            domain.setParceiro(parceiro);
        }
        if (entity.getResponsavel() != null) {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(entity.getResponsavel().getIdFuncionario());
            funcionario.setNome(entity.getResponsavel().getNome());
            CpfVo cpfVo = new CpfVo(entity.getResponsavel().getCpf());
            funcionario.setCpf(cpfVo);
            TelefoneVo telefoneVo = new TelefoneVo(entity.getResponsavel().getTelefone());
            funcionario.setTelefone(telefoneVo);
            EmailVo emailVo = new EmailVo(entity.getResponsavel().getEmail());
            funcionario.setEmail(emailVo);
            domain.setResponsavel(funcionario);
        }
        return domain;
    }

    public static Lote ofCadsEntity(LoteEntity entity) {
        Lote domain = new Lote();
        domain.setIdLote(entity.getIdLote());
        domain.setDescricao(entity.getDescricao());
        domain.setDataEntrada(entity.getDataEntrada());
        if (entity.getParceiro() != null) {
            Parceiro parceiro = new Parceiro();
            parceiro.setId(entity.getParceiro().getIdParceiro());
            domain.setParceiro(parceiro);
        }
        if (entity.getResponsavel() != null) {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(entity.getResponsavel().getIdFuncionario());
            domain.setResponsavel(funcionario);
        }
        return domain;
    }

    public static LoteEntity ofCads(Lote domain) {

        LoteEntity entity = new LoteEntity();
        entity.setIdLote(domain.getIdLote());
        entity.setDescricao(domain.getDescricao());
        entity.setDataEntrada(domain.getDataEntrada());

        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setIdFuncionario(domain.getResponsavel().getIdFuncionario());
        entity.setResponsavel(funcionario);

        ParceiroEntity parceiro = new ParceiroEntity();
        parceiro.setIdParceiro(domain.getParceiro().getId());
        entity.setParceiro(parceiro);

        return entity;
    }

    public static LoteEntity ofDomain(Lote domain) {

        LoteEntity entity = new LoteEntity();
        entity.setIdLote(domain.getIdLote());
        entity.setDescricao(domain.getDescricao());
        entity.setDataEntrada(domain.getDataEntrada());

        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setNome(domain.getResponsavel().getNome());
        funcionario.setTelefone(domain.getResponsavel().getTelefone().getValue());
        funcionario.setEmail(domain.getResponsavel().getEmail().getValue());
        entity.setResponsavel(funcionario);

        ParceiroEntity parceiro = new ParceiroEntity();
        parceiro.setCategoria(domain.getParceiro().getCategoria());
        parceiro.setNome(domain.getParceiro().getNome());
        parceiro.setTelefone(domain.getParceiro().getTelefone());
        parceiro.setEmail(domain.getParceiro().getEmail().getValue());
        entity.setParceiro(parceiro);

        return entity;
    }

    public static LoteEmEstoqueResponse toLoteEmEstoqueResponse(LoteEmEstoqueDto dto){
        return new LoteEmEstoqueResponse(
                dto.getIdLote(),
                dto.getNomeItem(),
                dto.getQtdItem(),
                dto.getIdItem(),
                dto.getPrecoItem() == null ? 0.0 : dto.getPrecoItem(),
                dto.getIdLoteItemEstoque(),
                dto.getFkCategoriaPai() != null && dto.getFkCategoriaPai() == 2
        );
    }

}
