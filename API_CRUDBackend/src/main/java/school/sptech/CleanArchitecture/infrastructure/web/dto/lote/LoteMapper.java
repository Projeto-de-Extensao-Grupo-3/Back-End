package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import school.sptech.CleanArchitecture.core.application.command.lote.AtualizarLotePorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.lote.CriarLoteCommand;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoteMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static CriarLoteCommand toCriarCommand(LoteRequestDto dto) {
        return new CriarLoteCommand(
                dto.getDescricao(),
                dto.getDataEntrada(),
                dto.getParceiro(),
                dto.getResponsavel()
        );
    }

    public static AtualizarLotePorIdCommand toAtualizarCommand(Integer id, LoteRequestDto dto) {
        return new AtualizarLotePorIdCommand(
                id,
                dto.getDescricao(),
                dto.getDataEntrada(),
                dto.getParceiro(),
                dto.getResponsavel()
        );
    }

    // Só são inicializados 2 valores da LoteResponseDto, pois os demais valores
    // serão preenchidos na controller que fara a requisição..
    public static LoteResponseDto toResponseDto(LoteEntity entity) {
        LoteResponseDto response = new LoteResponseDto();
        response.setIdLote(entity.getIdLote());
        response.setDescricao(entity.getDescricao());
        response.setDataEntrada(entity.getDataEntrada().format(DATE_FORMATTER));
        LoteFuncionarioResponseDto funcionarioResponseDto = new LoteFuncionarioResponseDto();
        funcionarioResponseDto.setNome(entity.getResponsavel().getNome());
        funcionarioResponseDto.setTelefone(entity.getResponsavel().getTelefone());
        funcionarioResponseDto.setEmail(entity.getResponsavel().getEmail());
        response.setResponsavel(funcionarioResponseDto);

        LoteParceiroResponseDto parceiroResponseDto = new LoteParceiroResponseDto();
        parceiroResponseDto.setCategoria(entity.getParceiro().getCategoria());
        parceiroResponseDto.setNome(entity.getParceiro().getNome());
        parceiroResponseDto.setTelefone(entity.getParceiro().getTelefone());
        parceiroResponseDto.setEmail(entity.getParceiro().getEmail());
        response.setParceiro(parceiroResponseDto);
        return response;
    }

}
