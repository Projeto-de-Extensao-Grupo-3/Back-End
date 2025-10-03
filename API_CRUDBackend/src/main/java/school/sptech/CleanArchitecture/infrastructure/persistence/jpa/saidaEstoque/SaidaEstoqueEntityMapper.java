package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.saidaEstoque;

import jakarta.validation.Valid;
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.*;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro.ParceiroEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque.*;

import java.util.List;

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
                new SaidaEstoqueFuncionarioResponseDto(funcionario.getNome(), funcionario.getSenha());

        LoteItemEstoque loteItemEstoque = domain.getLoteItemEstoque();
        SaidaEstoqueLoteItemEstoqueResponseDto loteItemEstoqueResponseDto =
                new SaidaEstoqueLoteItemEstoqueResponseDto(loteItemEstoque.getQtdItem(), loteItemEstoque.getPreco());

        Parceiro parceiro = domain.getCostureira();
        SaidaEstoqueCostureiraResponseDto costureiraResponseDto = null;
        if (domain.getCostureira() != null){
            costureiraResponseDto = new SaidaEstoqueCostureiraResponseDto(parceiro.getNome(), parceiro.getTelefone(), parceiro.getEmail().getValue());
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
