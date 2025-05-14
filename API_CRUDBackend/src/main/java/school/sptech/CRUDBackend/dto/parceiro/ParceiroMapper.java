package school.sptech.CRUDBackend.dto.parceiro;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.Parceiro;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs Parceiro")
public class ParceiroMapper {
    public static Parceiro toEntity(ParceiroRequestDto requestDto) {
        return new Parceiro(
                null,
                requestDto.getCategoria(),
                requestDto.getNome(),
                requestDto.getTelefone(),
                requestDto.getEmail(),
                requestDto.getEndereco(),
                requestDto.getIdentificacao()
        );
    }

    public static ParceiroResponseDto toResponseDto(Parceiro servico) {
        return new ParceiroResponseDto(
                servico.getIdParceiro(),
                servico.getCategoria(),
                servico.getNome(),
                servico.getTelefone(),
                servico.getEmail(),
                servico.getEndereco(),
                servico.getIdentificacao()
        );
    }

    public static List<ParceiroResponseDto> toResponseDtos(List<Parceiro> sericos) {
        return sericos
                .stream()
                .map(ParceiroMapper::toResponseDto)
                .toList();
    }
}
