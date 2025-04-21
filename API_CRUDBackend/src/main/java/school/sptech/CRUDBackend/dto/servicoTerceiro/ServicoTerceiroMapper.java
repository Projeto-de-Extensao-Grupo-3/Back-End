package school.sptech.CRUDBackend.dto.servicoTerceiro;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.ServicoTerceiro;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs ServicoTerceiro")
public class ServicoTerceiroMapper {
    public static ServicoTerceiro toEntity(ServicoTerceiroRequestDto requestDto) {
        return new ServicoTerceiro(
                null,
                requestDto.getCategoria(),
                requestDto.getNome(),
                requestDto.getTelefone(),
                requestDto.getEmail(),
                requestDto.getEndereco(),
                requestDto.getIdentificacao()
        );
    }

    public static ServicoTerceiroResponseDto toResponseDto(ServicoTerceiro servico) {
        return new ServicoTerceiroResponseDto(
                servico.getIdServicoTerceiro(),
                servico.getCategoria(),
                servico.getNome(),
                servico.getTelefone(),
                servico.getEmail(),
                servico.getEndereco(),
                servico.getIdentificacao()
        );
    }

    public static List<ServicoTerceiroResponseDto> toResponseDtos(List<ServicoTerceiro> sericos) {
        return sericos
                .stream()
                .map(ServicoTerceiroMapper::toResponseDto)
                .toList();
    }
}
