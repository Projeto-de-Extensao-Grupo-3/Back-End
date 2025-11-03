package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.saidaEstoque;

import jakarta.validation.Valid;
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.*;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.TelefoneVo;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro.ParceiroEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque.*;

import java.util.List;

public class SaidaEstoqueEntityMapper {
    public static SaidaEstoqueEntity toEntity(SaidaEstoque saidaEstoque) {

        FuncionarioEntity funcionario = null;
        if (saidaEstoque.getResponsavel() != null){
            funcionario = new FuncionarioEntity();
            funcionario.setIdFuncionario(saidaEstoque.getResponsavel().getIdFuncionario());
            funcionario.setNome(saidaEstoque.getResponsavel().getNome());

            if (saidaEstoque.getResponsavel().getTelefone() != null){
                funcionario.setTelefone(saidaEstoque.getResponsavel().getTelefone().getValue());
            }
            if(saidaEstoque.getResponsavel().getEmail() != null){
                funcionario.setEmail(saidaEstoque.getResponsavel().getEmail().getValue());
            }
        }

        LoteItemEstoqueEntity loteItemEstoque = new LoteItemEstoqueEntity();
        loteItemEstoque.setIdLoteItemEstoque(saidaEstoque.getLoteItemEstoque().getIdLoteItemEstoque());
        loteItemEstoque.setQtdItem(saidaEstoque.getLoteItemEstoque().getQtdItem());
        loteItemEstoque.setPreco(saidaEstoque.getLoteItemEstoque().getPreco());

        ParceiroEntity costureira = null;
        if (saidaEstoque.getCostureira() != null) {
            costureira = new ParceiroEntity();
            costureira.setIdParceiro(saidaEstoque.getCostureira().getId());
            costureira.setCategoria(saidaEstoque.getCostureira().getCategoria());
            costureira.setNome(saidaEstoque.getCostureira().getNome());
            costureira.setTelefone(saidaEstoque.getCostureira().getTelefone());

            if(saidaEstoque.getCostureira().getEmail() != null){
                costureira.setEmail(saidaEstoque.getCostureira().getEmail().getValue());
            }

            costureira.setEndereco(saidaEstoque.getCostureira().getEndereco());
        }

        return new SaidaEstoqueEntity(
                saidaEstoque.getIdSaidaEstoque(),
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

        Funcionario funcionario = null;
        if (saidaEstoque.getResponsavel() != null) {
            funcionario = new Funcionario();
            funcionario.setIdFuncionario(saidaEstoque.getResponsavel().getIdFuncionario());
            funcionario.setNome(saidaEstoque.getResponsavel().getNome());
            if (saidaEstoque.getResponsavel().getTelefone() != null) {
                funcionario.setTelefone(new TelefoneVo(saidaEstoque.getResponsavel().getTelefone()));
            }
            if (saidaEstoque.getResponsavel().getEmail() != null) {
                funcionario.setEmail(new EmailVo(saidaEstoque.getResponsavel().getEmail()));
            }
        }

        LoteItemEstoque loteItemEstoque = null;
        if (saidaEstoque.getLoteItemEstoque() != null) {
            loteItemEstoque = new LoteItemEstoque();
            loteItemEstoque.setIdLoteItemEstoque(saidaEstoque.getLoteItemEstoque().getIdLoteItemEstoque());
            loteItemEstoque.setQtdItem(saidaEstoque.getLoteItemEstoque().getQtdItem());
            loteItemEstoque.setPreco(saidaEstoque.getLoteItemEstoque().getPreco());
        }

        Parceiro costureira = null;
        if (saidaEstoque.getCostureira() != null) {
            costureira = new Parceiro();
            costureira.setId(saidaEstoque.getCostureira().getIdParceiro());
            costureira.setCategoria(saidaEstoque.getCostureira().getCategoria());
            costureira.setTelefone(saidaEstoque.getCostureira().getTelefone());
            costureira.setNome(saidaEstoque.getCostureira().getNome());
            costureira.setEmail(new EmailVo(saidaEstoque.getCostureira().getEmail()));
            costureira.setEndereco(saidaEstoque.getCostureira().getEndereco());
        }

        return new SaidaEstoque(
                saidaEstoque.getIdSaidaEstoque(),
                saidaEstoque.getData(),
                saidaEstoque.getHora(),
                saidaEstoque.getQtdSaida(),
                saidaEstoque.getMotivoSaida(),
                funcionario,
                loteItemEstoque,
                costureira
        );
    }

    public static SaidaEstoqueCadastrarCommand toCadastrarCommand(@Valid SaidaEstoqueRequestDto saidaCadastro) {
        SaidaEstoqueFuncionarioCommand responsavelCommand =
                new SaidaEstoqueFuncionarioCommand(saidaCadastro.getResponsavel().getIdFuncionario());
        SaidaEstoqueLoteItemEstoqueCommand loteItemEstoqueCommand =
                new SaidaEstoqueLoteItemEstoqueCommand(saidaCadastro.getLoteItemEstoque().getIdLoteItemEstoque());
        SaidaEstoqueCostureiraCommand costureiraCommand = null;
            if (saidaCadastro.getCostureira() != null){
                costureiraCommand = new SaidaEstoqueCostureiraCommand(saidaCadastro.getCostureira().getIdCostureira());
            }
                return new SaidaEstoqueCadastrarCommand(
                        saidaCadastro.getData(),
                        saidaCadastro.getHora(),
                        saidaCadastro.getQtdSaida(),
                        saidaCadastro.getMotivoSaida(),
                        responsavelCommand,
                        loteItemEstoqueCommand,
                        costureiraCommand,
                        saidaCadastro.getQtdSaida()
                );
    }

    public static SaidaEstoqueResponseDto toResponseDto(SaidaEstoque domain) {
        Funcionario funcionario = domain.getResponsavel();
        SaidaEstoqueFuncionarioResponseDto responsavelDto =
                new SaidaEstoqueFuncionarioResponseDto(funcionario.getNome(), funcionario.getEmail().getValue());

        LoteItemEstoque loteItemEstoque = domain.getLoteItemEstoque();
        SaidaEstoqueLoteItemEstoqueResponseDto loteItemEstoqueResponseDto =
                new SaidaEstoqueLoteItemEstoqueResponseDto(loteItemEstoque.getQtdItem(), loteItemEstoque.getPreco());

        SaidaEstoqueCostureiraResponseDto costureiraResponseDto = null;
        if (domain.getCostureira() != null){
            Parceiro parceiro = domain.getCostureira();
            costureiraResponseDto = new SaidaEstoqueCostureiraResponseDto();
            costureiraResponseDto.setNome(parceiro.getNome());
            costureiraResponseDto.setTelefone(parceiro.getTelefone());
            if (parceiro.getEmail() != null){
                costureiraResponseDto.setEmail(parceiro.getEmail().getValue());
            }
        }
        return new SaidaEstoqueResponseDto(
                domain.getIdSaidaEstoque(),
                domain.getData(),
                domain.getHora(),
                domain.getQtdSaida(),
                domain.getMotivoSaida(),
                responsavelDto,
                loteItemEstoqueResponseDto,
                costureiraResponseDto
        );
    }

    public static List<SaidaEstoqueResponseDto> toResponseDtos(List<SaidaEstoque> saidas) {
        return saidas
                .stream()
                .map(SaidaEstoqueEntityMapper::toResponseDto)
                .toList();
    }

    public static SaidaEstoqueAtualizarPorIdCommand toAtualizarCommand(Integer id, SaidaEstoqueRequestDto saidaParaAtualizar) {
        SaidaEstoqueFuncionarioCommand responsavelCommand =
                new SaidaEstoqueFuncionarioCommand(saidaParaAtualizar.getResponsavel().getIdFuncionario());
        SaidaEstoqueLoteItemEstoqueCommand loteItemEstoqueCommand =
                new SaidaEstoqueLoteItemEstoqueCommand(saidaParaAtualizar.getLoteItemEstoque().getIdLoteItemEstoque());
        SaidaEstoqueCostureiraCommand costureiraCommand = null;
        if (saidaParaAtualizar.getCostureira() != null){
            costureiraCommand = new SaidaEstoqueCostureiraCommand(saidaParaAtualizar.getCostureira().getIdCostureira());
        }
        return new SaidaEstoqueAtualizarPorIdCommand(
                id,
                saidaParaAtualizar.getData(),
                saidaParaAtualizar.getHora(),
                saidaParaAtualizar.getQtdSaida(),
                saidaParaAtualizar.getMotivoSaida(),
                responsavelCommand,
                loteItemEstoqueCommand,
                costureiraCommand
        );
    }
}
