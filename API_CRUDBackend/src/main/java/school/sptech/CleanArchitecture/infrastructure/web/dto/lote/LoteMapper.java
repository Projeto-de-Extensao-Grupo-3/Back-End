package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import school.sptech.CleanArchitecture.core.application.command.lote.AtualizarLotePorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.lote.CriarLoteCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoteMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static CriarLoteCommand toCriarCommand(LoteRequestDto dto) {
        Parceiro parceiro = null;
        if (dto.getParceiro() != null && dto.getParceiro().getIdParceiro() != null) {
            parceiro = new Parceiro();
            parceiro.setId(dto.getParceiro().getIdParceiro());
        }

        Funcionario funcionario = null;
        if (dto.getResponsavel() != null && dto.getResponsavel().getIdFuncionario() != null) {
            funcionario = new Funcionario();
            funcionario.setIdFuncionario(dto.getResponsavel().getIdFuncionario());
        }

        LocalDateTime dataEntrada = LocalDateTime.parse(dto.getDataEntrada(), DATE_FORMATTER);

        return new CriarLoteCommand(
                dto.getDescricao(),
                dataEntrada,
                parceiro,
                funcionario
        );
    }

    public static AtualizarLotePorIdCommand toAtualizarCommand(Integer id, LoteRequestDto dto) {
        Parceiro parceiro = null;
        if (dto.getParceiro() != null && dto.getParceiro().getIdParceiro() != null) {
            parceiro = new Parceiro();
            parceiro.setId(dto.getParceiro().getIdParceiro());
        }

        Funcionario funcionario = null;
        if (dto.getResponsavel() != null && dto.getResponsavel().getIdFuncionario() != null) {
            funcionario = new Funcionario();
            funcionario.setIdFuncionario(dto.getResponsavel().getIdFuncionario());
        }

        LocalDateTime dataEntrada = LocalDateTime.parse(dto.getDataEntrada(), DATE_FORMATTER);

        return new AtualizarLotePorIdCommand(
                id,
                dto.getDescricao(),
                dataEntrada,
                parceiro,
                funcionario
        );
    }

    public static LoteResponseDto toResponseDto(LoteEntity entity) {
        LoteResponseDto dto = new LoteResponseDto();
        dto.setIdLote(entity.getIdLote());
        dto.setDescricao(entity.getDescricao());
        dto.setDataEntrada(entity.getDataEntrada().format(DATE_FORMATTER));

        // Mapear Parceiro
        if (entity.getParceiro() != null) {
            LoteParceiroResponseDto parceiroDto = new LoteParceiroResponseDto();
            parceiroDto.setCategoria(entity.getParceiro().getCategoria());
            parceiroDto.setNome(entity.getParceiro().getNome());
            parceiroDto.setTelefone(entity.getParceiro().getTelefone());
            parceiroDto.setEmail(entity.getParceiro().getEmail() != null ?
                entity.getParceiro().getEmail().getValue() : null);
            dto.setParceiro(parceiroDto);
        }

        // Mapear Funcionario
        if (entity.getResponsavel() != null) {
            LoteFuncionarioResponseDto funcionarioDto = new LoteFuncionarioResponseDto();
            funcionarioDto.setNome(entity.getResponsavel().getNome());
            funcionarioDto.setTelefone(entity.getResponsavel().getTelefone() != null ?
                entity.getResponsavel().getTelefone().getValue() : null);
            funcionarioDto.setEmail(entity.getResponsavel().getEmail() != null ?
                entity.getResponsavel().getEmail().getValue() : null);
            dto.setResponsavel(funcionarioDto);
        }

        return dto;
    }
}
