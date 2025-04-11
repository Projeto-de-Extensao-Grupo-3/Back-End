package school.sptech.CRUDBackend.dto.servicoTerceiro;

import school.sptech.CRUDBackend.entity.ServicoTerceiro;

public class ServicoTerceiroMapper {
    public static ServicoTerceiro toEntity(ServicoTerceiroRequestDto requestDto) {
        ServicoTerceiro servico = new ServicoTerceiro();
        servico.setCategoria(requestDto.getCategoria());
        servico.setNome(requestDto.getNome());
        servico.setTelefone(requestDto.getTelefone());
        servico.setEmail(requestDto.getEmail());
        servico.setEndereco(requestDto.getEndereco());
        servico.setIdentificacao(requestDto.getIdentificacao());
        return servico;
    }

    public static ServicoTerceiroResponseDto toResponseDto(ServicoTerceiro servico) {
        ServicoTerceiroResponseDto responseDto = new ServicoTerceiroResponseDto();
        responseDto.setCategoria(servico.getCategoria());
        responseDto.setNome(servico.getNome());
        responseDto.setTelefone(servico.getTelefone());
        responseDto.setEmail(servico.getEmail());
        responseDto.setEndereco(servico.getEndereco());
        responseDto.setIdentificacao(servico.getIdentificacao());
        return responseDto;
    }
}
