package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import school.sptech.CleanArchitecture.core.application.command.lote.AtualizarLotePorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.lote.CriarLoteCommand;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoteMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static CriarLoteCommand toCriarCommand(LoteRequestDto dto) {
        LocalDateTime dataEntrada = LocalDateTime.parse(dto.getDataEntrada(), DATE_FORMATTER);
        return new CriarLoteCommand(
                dto.getDescricao(),
                dataEntrada,
                dto.getParceiro(),
                dto.getResponsavel()
        );
    }


    public static AtualizarLotePorIdCommand toAtualizarCommand(Integer id, LoteRequestDto dto) {
        LocalDateTime dataEntrada = LocalDateTime.parse(dto.getDataEntrada(), DATE_FORMATTER);

        return new AtualizarLotePorIdCommand(
                id,
                dto.getDescricao(),
                dataEntrada,
                dto.getParceiro(),
                dto.getResponsavel()
        );
    }

    public static LoteResponseDto toResponseDto(LoteEntity entity) {
        LoteResponseDto response = new LoteResponseDto();
        response.setIdLote(entity.getIdLote());
        response.setDescricao(entity.getDescricao());
        response.setDataEntrada(entity.getDataEntrada().format(DATE_FORMATTER));

        // Mapear responsável (funcionário)
        if (entity.getResponsavel() != null) {
            LoteFuncionarioResponseDto responsavel = new LoteFuncionarioResponseDto();
            responsavel.setNome(entity.getResponsavel().getNome());
            responsavel.setTelefone(entity.getResponsavel().getTelefone());
            responsavel.setEmail(entity.getResponsavel().getEmail());
            response.setResponsavel(responsavel);
        }

        // Mapear parceiro
        if (entity.getParceiro() != null) {
            LoteParceiroResponseDto parceiro = new LoteParceiroResponseDto();
            parceiro.setCategoria(entity.getParceiro().getCategoria());
            parceiro.setNome(entity.getParceiro().getNome());
            parceiro.setTelefone(entity.getParceiro().getTelefone());
            parceiro.setEmail(entity.getParceiro().getEmail());
            response.setParceiro(parceiro);
        }

        return response;
    }

}
