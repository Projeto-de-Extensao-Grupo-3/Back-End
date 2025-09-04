package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria;

import school.sptech.CleanArchitecture.core.application.command.categoria.CategoriaAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.command.categoria.CategoriaPaiCommand;
import school.sptech.CleanArchitecture.core.application.command.categoria.CriarCategoriaCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;
import school.sptech.CleanArchitecture.infrastructure.web.dto.categoria.CategoriaRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.categoria.CategoriaResponseDto;

import java.util.Objects;

public class CategoriaEntityMapper {

    public static CategoriaEntity ofDomain (Categoria domain){
        if (Objects.isNull(domain)) {
            return null;
        }
        CategoriaEntity categoriaPai = null;
        if (domain.getCategoriaPai() != null) {
            categoriaPai = new CategoriaEntity();
            categoriaPai.setIdCategoria(domain.getCategoriaPai().getIdCategoria());
        }
        var entity = new CategoriaEntity();
        entity.setIdCategoria(domain.getIdCategoria());
        entity.setNome(domain.getNome());
        entity.setCategoriaPai(categoriaPai);

        return entity;
    }

    public static Categoria ofEntity(CategoriaEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        Categoria categoriaPai = null;
        if (entity.getCategoriaPai() != null) {
            categoriaPai = new Categoria();
            categoriaPai.setIdCategoria(entity.getCategoriaPai().getIdCategoria());
        }
        var domain = new Categoria();
        domain.setIdCategoria(entity.getIdCategoria());
        domain.setNome(entity.getNome());
        domain.setCategoriaPai(categoriaPai);

        return domain;
    }

    public static CategoriaResponseDto toResponseDto(CategoriaEntity categoria) {
        return new CategoriaResponseDto(
                categoria.getIdCategoria(),
                categoria.getNome()
        );
    }

    public static CategoriaAtualizarCommand toAtualzarCommand(Integer id, CategoriaRequestDto dto){
        CategoriaPaiCommand categoriaPaiCommand = new CategoriaPaiCommand(dto.getCategoriaPai().getIdCategoria());
        return new CategoriaAtualizarCommand(id, dto.getNome(), categoriaPaiCommand);
    }

    public static CriarCategoriaCommand toCriarCommand(CategoriaRequestDto dto) {
        CategoriaPaiCommand categoriaPaiCommand = new CategoriaPaiCommand(dto.getCategoriaPai().getIdCategoria());
        return new CriarCategoriaCommand(dto.getNome(), categoriaPaiCommand);
    }

    public static CategoriaEntity toEntity(CriarCategoriaCommand command) {
        CategoriaEntity categoriaPai = null;
        if (command.categoriaPai() != null) {
            categoriaPai = new CategoriaEntity();
            categoriaPai.setIdCategoria(command.categoriaPai().idCategoria());
        }
        return new CategoriaEntity(
                null,
                command.nome(),
                categoriaPai
        );
    }
}
