package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.AtualizarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.CriarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.LoteItemEstoqueItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.LoteItemEstoqueLoteCommand;
import school.sptech.CleanArchitecture.core.domain.entity.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntity;

import java.util.List;
import java.util.Set;

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
        LoteItemEstoqueItemEstoqueCommand itemEstoque = new LoteItemEstoqueItemEstoqueCommand(dto.getItemEstoque());

        LoteItemEstoqueLoteCommand lote = new LoteItemEstoqueLoteCommand(dto.getLote());

        return new AtualizarLoteItemEstoqueCommand(
                id,
                dto.getQtdItem(),
                dto.getPreco(),
                itemEstoque,
                lote
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

    public static LoteItemEstoque ofAtualizarCommand(AtualizarLoteItemEstoqueCommand command) {
        ItemEstoque itemEstoque = new ItemEstoque();
        itemEstoque.setIdItemEstoque(command.itemEstoque().idItemEstoque());

        Lote lote = new Lote();
        lote.setIdLote(command.lote().idLote());

        return new LoteItemEstoque(
                command.idLoteItemEstoque(),
                command.qtdItem(),
                command.preco(),
                itemEstoque,
                lote
        );
    }

    public static LoteItemEstoque ofCadastrarCommand(CriarLoteItemEstoqueCommand command) {

        ItemEstoque itemEstoque = new ItemEstoque();
        itemEstoque.setIdItemEstoque(command.itemEstoque());


        Lote lote = new Lote();
        lote.setIdLote(command.lote());

        return new LoteItemEstoque(
                command.qtdItem(),
                command.preco(),
                itemEstoque,
                lote
        );
    }
}
