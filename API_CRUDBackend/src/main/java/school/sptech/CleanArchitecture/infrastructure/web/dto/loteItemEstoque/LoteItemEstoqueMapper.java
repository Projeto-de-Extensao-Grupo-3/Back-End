package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.AtualizarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.CriarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntity;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs LoteItemEstoque.")
public class LoteItemEstoqueMapper {

    public static CriarLoteItemEstoqueCommand toCriarCommand(LoteItemEstoqueRequestDto dto) {
        return new CriarLoteItemEstoqueCommand(
                dto.getQtdItem(),
                dto.getPreco(),
                dto.getItemEstoque(),
                dto.getLote()
        );
    }

    public static AtualizarLoteItemEstoqueCommand toAtualizarCommand(Integer id, LoteItemEstoqueRequestDto dto) {
        return new AtualizarLoteItemEstoqueCommand(
                id,
                dto.getQtdItem(),
                dto.getPreco(),
                dto.getItemEstoque(),
                dto.getLote()
        );
    }

    public static LoteItemEstoqueResponseDto toResponseDto(LoteItemEstoqueEntity entity) {
        return new LoteItemEstoqueResponseDto(
                entity.getIdLoteItemEstoque(),
                entity.getQtdItem(),
                entity.getPreco(),
                entity.getItemEstoque().getIdItemEstoque(),
                entity.getLote().getIdLote()
        );
    }
}
