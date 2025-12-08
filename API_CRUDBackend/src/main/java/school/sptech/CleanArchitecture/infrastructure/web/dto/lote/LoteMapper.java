package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import school.sptech.CleanArchitecture.core.application.command.lote.AtualizarLotePorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.lote.CriarLoteCommand;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoteMapper {
    private static String bucket;

    @Value("${aws.s3.nome-bucket}")
    public void setBucket(String value) {
        LoteMapper.bucket = value;
    }

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

    public static LoteResponseDto toCadastroResponseDto(LoteEntity entity) {
        LoteResponseDto response = new LoteResponseDto();
        response.setIdLote(entity.getIdLote());
        response.setDescricao(entity.getDescricao());
        response.setDataEntrada(entity.getDataEntrada().format(DATE_FORMATTER));
        return response;
    }

    public static LoteDetalhadoResponseDto ToLoteDetalhadoResponseDto() {
        LoteDetalhadoResponseDto response = new LoteDetalhadoResponseDto();


        return response;
    }

    public static LoteDetalhadoItemDto toLoteDetalhadoItemDto(LoteDetalhadoDto loteDetalhadoDto) {
        LoteDetalhadoItemDto response = new LoteDetalhadoItemDto();
        response.setNomeItem(loteDetalhadoDto.getDescricao());
        response.setUrl("https://" + bucket + ".s3.us-east-1.amazonaws.com/" + loteDetalhadoDto.getUrl());
        response.setQtdEntrada(loteDetalhadoDto.getQtdEntrada());
        response.setQtdSaida(loteDetalhadoDto.getQtdSaida());

        return response;
    }

    public static List<LoteDetalhadoResponseDto> ToLoteDetalhadoResponseDtoList(List<LoteDetalhadoDto> loteDetalhadoDtos) {

        List<LoteDetalhadoResponseDto> response = new ArrayList<>();
        int i = 0;
        while (i < loteDetalhadoDtos.size()) {
            // ID do lote, para comparar com outros
            int idLote = loteDetalhadoDtos.get(i).getIdLote();

            // Criando um objeto de lote, que contem varios itens
            LoteDetalhadoResponseDto lote = new LoteDetalhadoResponseDto(idLote, loteDetalhadoDtos.get(i).getDtEntrada(), loteDetalhadoDtos.get(i).getMotivo());

            // Adicionando esse primeiro item para a lista de itens
            List<LoteDetalhadoItemDto> itensDoLote = new ArrayList<>();
            itensDoLote.add(toLoteDetalhadoItemDto(loteDetalhadoDtos.get(i)));

            while (i < loteDetalhadoDtos.size() - 1 && idLote == loteDetalhadoDtos.get(i + 1).getIdLote()) {
                // Adicionando item com mesmo lote para lista de itens
                itensDoLote.add(toLoteDetalhadoItemDto(loteDetalhadoDtos.get(i + 1)));
                i++;
            }

            // Adicionando lista de itens ao lote
            lote.setItens(itensDoLote);

            // Adicionando lote a lista de lotes
            response.add(lote);

            i++;
        }

        return response;
    }

}
