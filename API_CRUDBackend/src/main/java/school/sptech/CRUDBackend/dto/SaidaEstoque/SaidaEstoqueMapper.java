package school.sptech.CRUDBackend.dto.SaidaEstoque;

import school.sptech.CRUDBackend.dto.permissao.PermissaoMapper;
import school.sptech.CRUDBackend.entity.SaidaEstoque;

import java.util.List;

public class SaidaEstoqueMapper {

    public static SaidaEstoque toEntity(SaidaEstoqueRequestDto requestDto){
        SaidaEstoque saidaEstoque = new SaidaEstoque();
        saidaEstoque.setData(requestDto.getData());
        saidaEstoque.setHora(requestDto.getHora());
        saidaEstoque.setQtdSaida(requestDto.getQtSaida());
        saidaEstoque.setMotivoSaida(requestDto.getMotivoSaida());
        return saidaEstoque;
    }

    public static SaidaEstoqueResponseDto toResponseDto(SaidaEstoque saidaEstoque){
        SaidaEstoqueResponseDto responseDto = new SaidaEstoqueResponseDto();

        responseDto.setIdSaida(saidaEstoque.getIdSaida());
        responseDto.setData(saidaEstoque.getData());
        responseDto.setHora(saidaEstoque.getHora());
        responseDto.setQtSaida(saidaEstoque.getQtdSaida());
        responseDto.setMotivoSaida(saidaEstoque.getMotivoSaida());
        return responseDto;
    }

    public static List<SaidaEstoqueResponseDto> toResponseDtos(List<SaidaEstoque> saidas){
        return saidas
                .stream()
                .map(SaidaEstoqueMapper::toResponseDto)
                .toList();
    }
}
