package school.sptech.CRUDBackend.dto.parceiro;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.Parceiroaa;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs Parceiro")
public class ParceiroMapper {
    public static Parceiroaa toEntity(ParceiroRequestDto requestDto) {
        return new Parceiroaa(
                null,
                requestDto.getCategoria(),
                requestDto.getNome(),
                requestDto.getTelefone(),
                requestDto.getEmail(),
                requestDto.getEndereco(),
                requestDto.getIdentificacao()
        );
    }

    public static ParceiroResponseDto toResponseDto(Parceiroaa servico) {
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

    public static List<ParceiroResponseDto> toResponseDtos(List<Parceiroaa> sericos) {
        return sericos
                .stream()
                .map(ParceiroMapper::toResponseDto)
                .toList();
    }
}
