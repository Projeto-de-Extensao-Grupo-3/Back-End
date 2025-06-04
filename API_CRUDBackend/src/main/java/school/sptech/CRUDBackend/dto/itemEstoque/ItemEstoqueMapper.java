package school.sptech.CRUDBackend.dto.itemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.Categoria;
import school.sptech.CRUDBackend.entity.Imagem;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.entity.Prateleira;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(description = "Classe de mapeamento de DTOs ItemEstoque.")
public class ItemEstoqueMapper {
    public static ItemEstoque toEntity(ItemEstoqueRequestDto requestDto) {

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(requestDto.getCategoria().getIdCategoria());

        Set<Categoria> caracteristicas = new HashSet<>();
        caracteristicas = requestDto.getCaracteristicas()
                .stream()
                .map(caracteristica
                        -> new Categoria(caracteristica.getIdCategoria(), null, null))
                .collect(Collectors.toSet());

        Prateleira prateleira = new Prateleira();
        prateleira.setIdPrateleira(requestDto.getPlateleira().getIdPrateleira());

        Imagem imagem = new Imagem(requestDto.getImagem().getIdImagem(), requestDto.getImagem().getUrl());

        return new ItemEstoque(
                null,
                requestDto.getDescricao(),
                requestDto.getComplemento(),
                requestDto.getPeso(),
                requestDto.getQtdMinimo(),
                requestDto.getQtdArmazenado(),
                categoria,
                caracteristicas,
                prateleira,
                null,
                requestDto.getPreco(),
                imagem
        );
    }

    public static ItemEstoqueResponseDto toResponseDto(ItemEstoque item) {

        Categoria categoria = item.getCategoria();
        ItemEstoqueCategoriaPaiResponseDto categoriaPai = new ItemEstoqueCategoriaPaiResponseDto(
                categoria.getCategoriaPai().getNome()
        );
        ItemEstoqueCategoriaResponseDto categoriaDto = new ItemEstoqueCategoriaResponseDto(
                categoria.getNome(), categoriaPai
        );
        Set<ItemEstoqueCaracteristicaResponseDto> caracteristicasDto = item.getCaracteristicas()
                .stream()
                .map(caracteristica -> new ItemEstoqueCaracteristicaResponseDto(caracteristica.getNome()))
                .collect(Collectors.toSet());

        Set<ItemEstoqueConfeccaoRoupaDto> confeccaoRoupaDto = item.getConfeccaoRoupa()
                .stream()
                .map(confeccaoRoupa ->
                        new ItemEstoqueConfeccaoRoupaDto(
                                confeccaoRoupa.getIdConfeccaoRoupa(),
                                new ItemEstoqueTecidoDto(
                                        confeccaoRoupa.getTecido().getIdItemEstoque(),
                                        confeccaoRoupa.getTecido().getDescricao()
                                ),
                                confeccaoRoupa.getQtdTecido()
                        )
                )
                .collect(Collectors.toSet());

        ItemEstoqueImagemResponseDto imagemDto = new ItemEstoqueImagemResponseDto(item.getImagem().getUrl());

        return new ItemEstoqueResponseDto(
                item.getIdItemEstoque(),
                item.getDescricao(),
                item.getPeso(),
                item.getQtdMinimo(),
                item.getQtdArmazenado(),
                categoriaDto,
                caracteristicasDto,
                confeccaoRoupaDto,
                item.getPreco(),
                imagemDto
        );
    }

    public static List<ItemEstoqueResponseDto> toResponseDtos(List<ItemEstoque> itensEstoque) {
        return itensEstoque
                .stream()
                .map(ItemEstoqueMapper::toResponseDto)
                .toList();
    }
}
