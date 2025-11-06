package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque;

import school.sptech.CleanArchitecture.core.application.command.itemEstoque.*;
import school.sptech.CleanArchitecture.core.domain.entity.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa.ConfeccaoRoupaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira.PrateleiraEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemEstoqueEntityMapper {

    public static ItemEstoqueEntity ofDomain (ItemEstoque domain){
        if (Objects.isNull(domain)) {
            return null;
        }

        var entity = new ItemEstoqueEntity();
        Categoria categoriaDomain = domain.getCategoria();
        CategoriaEntity categoria = new CategoriaEntity(categoriaDomain.getIdCategoria(), categoriaDomain.getNome());

        Categoria categoriaPai;
        if(domain.getCategoria().getCategoriaPai() != null){
            categoriaPai = domain.getCategoria().getCategoriaPai();
            categoria.setCategoriaPai(new CategoriaEntity(categoriaPai.getIdCategoria(), categoriaPai.getNome()));
        }


        Set<Categoria> caracteristicas = domain.getCaracteristicas();
        Set<CategoriaEntity> caracteristicasEntity = null;
        if (caracteristicas != null){
            caracteristicasEntity = caracteristicas.stream()
                    .map(c -> {
                                CategoriaEntity categoriaEntity = new CategoriaEntity();
                                categoriaEntity.setIdCategoria(c.getIdCategoria());
                                categoriaEntity.setNome(c.getNome());
                                if(c.getCategoriaPai() != null){
                                    Categoria categoriaPai2 = domain.getCategoria().getCategoriaPai();
                                    categoriaEntity.setCategoriaPai(new CategoriaEntity(categoriaPai2.getIdCategoria(), categoriaPai2.getNome()));
                                }
                                return categoriaEntity;
                            }
                    ).collect(Collectors.toSet());
        }


        PrateleiraEntity prateleiraEntity = new PrateleiraEntity();
        if (domain.getPrateleira() != null){
            prateleiraEntity = new PrateleiraEntity(domain.getPrateleira().getIdPrateleira(), domain.getPrateleira().getCodigo());
        }

        Set<ConfeccaoRoupa> confeccaoRoupa = domain.getConfeccaoRoupa();
        Set<ConfeccaoRoupaEntity> confeccaoRoupaEntities = null;
        if(confeccaoRoupa != null){
            confeccaoRoupaEntities = confeccaoRoupa.stream()
                    .map(cr -> {
                        ConfeccaoRoupaEntity confeccaoEntity = new ConfeccaoRoupaEntity();
                        confeccaoEntity.setIdConfeccaoRoupa(cr.getIdConfeccaoRoupa());// só seta o id
                        confeccaoEntity.setRoupa(new ItemEstoqueEntity(cr.getRoupa().getIdItemEstoque(), cr.getRoupa().getDescricao()));
                        confeccaoEntity.setTecido(new ItemEstoqueEntity(cr.getTecido().getIdItemEstoque(), cr.getTecido().getDescricao()));
                        confeccaoEntity.setQtdTecido(cr.getQtdTecido());
                        return confeccaoEntity;
                    })
                    .collect(Collectors.toSet());
        }

        ImagemEntity imagemEntity = new ImagemEntity();
        if (domain.getImagem() != null){
            imagemEntity = new ImagemEntity(domain.getImagem().getIdImagem());
        }

        entity.setIdItemEstoque(domain.getIdItemEstoque());
        entity.setDescricao(domain.getDescricao());
        entity.setComplemento(domain.getComplemento());
        entity.setPeso(domain.getPeso());
        entity.setQtdMinimo(domain.getQtdMinimo());
        entity.setQtdArmazenado(domain.getQtdArmazenado());
        entity.setNotificar(domain.getNotificar());
        entity.setCategoria(categoria);
        entity.setCaracteristicas(caracteristicasEntity);
        entity.setPrateleira(prateleiraEntity);
        entity.setConfeccaoRoupa(confeccaoRoupaEntities);
        entity.setPreco(domain.getPreco());
        entity.setImagem(imagemEntity);

        return entity;
    }

    public static ItemEstoque ofEntity(ItemEstoqueEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        var domain = new ItemEstoque();
        CategoriaEntity categoriaEntity = entity.getCategoria();
        Categoria categoria = new Categoria(categoriaEntity.getIdCategoria(), categoriaEntity.getNome());

        CategoriaEntity categoriaPai =  null;
        if (entity.getCategoria().getCategoriaPai() != null) {
            categoriaPai = entity.getCategoria().getCategoriaPai();
            categoria.setCategoriaPai(new Categoria(categoriaPai.getIdCategoria(), categoriaPai.getNome()));
        }

        Set<Categoria> caracteristicasEntity = null;
        if (entity.getCaracteristicas() != null){
            Set<CategoriaEntity> caracteristicas = entity.getCaracteristicas();
            caracteristicasEntity = caracteristicas.stream()
                            .map(c -> {
                                        Categoria categoriaDomain = new Categoria();
                                        categoriaDomain.setIdCategoria(c.getIdCategoria());
                                        categoriaDomain.setNome(c.getNome());
                                        if(c.getCategoriaPai() != null){
                                            CategoriaEntity categoriaPai2 = entity.getCategoria().getCategoriaPai();
                                            categoriaDomain.setCategoriaPai(new Categoria(categoriaPai2.getIdCategoria(), categoriaPai2.getNome()));
                                        }
                                        return categoriaDomain;
                                    }
                            ).collect(Collectors.toSet());
        }

        Prateleira prateleira = new Prateleira();
        if (entity.getPrateleira() != null){
            prateleira = new Prateleira(entity.getPrateleira().getIdPrateleira(), entity.getPrateleira().getCodigo());
        }

        Set<ConfeccaoRoupa> confeccaoRoupas = null;
        if (entity.getConfeccaoRoupa() != null){
            Set<ConfeccaoRoupaEntity> confeccaoRoupaEntity = entity.getConfeccaoRoupa();
            confeccaoRoupas = confeccaoRoupaEntity.stream()
                    .map(cr -> {
                        ConfeccaoRoupa confeccao = new ConfeccaoRoupa();
                        confeccao.setIdConfeccaoRoupa(cr.getIdConfeccaoRoupa());
                        confeccao.setTecido(new ItemEstoque(cr.getTecido().getIdItemEstoque(), cr.getTecido().getDescricao()));// só seta o id
                        confeccao.setRoupa(new ItemEstoque(cr.getRoupa().getIdItemEstoque(), cr.getRoupa().getDescricao()));// só seta o id
                        confeccao.setQtdTecido(cr.getQtdTecido());
                        return confeccao;
                    })
                    .collect(Collectors.toSet());
        }

        Imagem imagem = new Imagem();
        if (entity.getImagem() != null){
            imagem.setIdImagem(entity.getImagem().getIdImagem());
            imagem.setUrl(entity.getImagem().getUrl());
        }

        domain.setIdItemEstoque(entity.getIdItemEstoque());
        domain.setDescricao(entity.getDescricao());
        domain.setComplemento(entity.getComplemento());
        domain.setPeso(entity.getPeso());
        domain.setQtdMinimo(entity.getQtdMinimo());
        domain.setQtdArmazenado(entity.getQtdArmazenado());
        domain.setNotificar(entity.getNotificar());
        domain.setCategoria(categoria);
        domain.setCaracteristicas(caracteristicasEntity);
        domain.setPrateleira(prateleira);
        domain.setConfeccaoRoupa(confeccaoRoupas);
        domain.setPreco(entity.getPreco());
        domain.setImagem(imagem);

        return domain;
    }

    public static ItemEstoqueEntity toEntity(ItemEstoqueRequestDto requestDto) {

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setIdCategoria(requestDto.getCategoria().getIdCategoria());

        Set<CategoriaEntity> caracteristicas = new HashSet<>();
        caracteristicas = requestDto.getCaracteristicas()
                .stream()
                .map(caracteristica
                        -> new CategoriaEntity(caracteristica.getIdCategoria(), null, null))
                .collect(Collectors.toSet());

        PrateleiraEntity prateleira = new PrateleiraEntity();
        prateleira.setIdPrateleira(requestDto.getPlateleira().getIdPrateleira());

        ImagemEntity imagem = new ImagemEntity(requestDto.getImagem().getIdImagem(), requestDto.getImagem().getUrl());

        return new ItemEstoqueEntity(
                null,
                requestDto.getDescricao(),
                requestDto.getComplemento(),
                requestDto.getPeso(),
                requestDto.getQtdMinimo(),
                requestDto.getQtdArmazenado(),
                requestDto.getNotificar(),
                categoria,
                caracteristicas,
                prateleira,
                null,
                requestDto.getPreco(),
                imagem
        );
    }

    public static ItemEstoqueResponseDto toResponseDto(ItemEstoqueEntity item) {

        CategoriaEntity categoria = item.getCategoria();
        ItemEstoqueCategoriaPaiResponseDto categoriaPai = new ItemEstoqueCategoriaPaiResponseDto(
                categoria.getCategoriaPai().getNome()
        );
        ItemEstoqueCategoriaResponseDto categoriaDto = new ItemEstoqueCategoriaResponseDto(
                item.getCategoria().getIdCategoria(), categoria.getNome(), categoriaPai
        );
        Set<ItemEstoqueCaracteristicaResponseDto> caracteristicasDto = item.getCaracteristicas()
                .stream()
                .map(caracteristica -> new ItemEstoqueCaracteristicaResponseDto(caracteristica.getIdCategoria(), caracteristica.getNome()))
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

        ItemEstoquePrateleiraResponseDto prateleiraDto = item.getPrateleira() != null
                ? new ItemEstoquePrateleiraResponseDto(item.getPrateleira().getIdPrateleira(), item.getPrateleira().getCodigo())
                : null;

        ItemEstoqueImagemResponseDto imagemDto = item.getImagem() != null
                ? new ItemEstoqueImagemResponseDto(item.getImagem().getIdImagem(), item.getImagem().getUrl())
                : null;

        return new ItemEstoqueResponseDto(
                item.getIdItemEstoque(),
                item.getDescricao(),
                item.getPeso(),
                item.getQtdMinimo(),
                item.getQtdArmazenado(),
                item.getComplemento(),
                item.getNotificar(),
                categoriaDto,
                caracteristicasDto,
                confeccaoRoupaDto,
                prateleiraDto,
                item.getPreco(),
                imagemDto
        );
    }

    public static List<ItemEstoqueResponseDto> toResponseDtosEntity(List<ItemEstoque> itensEstoque) {
        return itensEstoque
                .stream()
                .map(ItemEstoqueEntityMapper::toResponseDto)
                .toList();
    }

    public static List<ItemEstoqueResponseDto> toResponseDtosDomain(List<ItemEstoqueEntity> itensEstoque) {
        return itensEstoque
                .stream()
                .map(ItemEstoqueEntityMapper::toResponseDto)
                .toList();
    }

    public static ItemEstoqueCadastrarCommand toCadastrarCommand(ItemEstoqueRequestDto dto) {

        ItemEstoqueCategoriaCommand categoria = new ItemEstoqueCategoriaCommand(dto.getCategoria().getIdCategoria());

        Set<ItemEstoqueCategoriaCommand> caracteristicasDto = dto.getCaracteristicas()
                .stream()
                .map(caracteristica -> new ItemEstoqueCategoriaCommand(caracteristica.getIdCategoria()))
                .collect(Collectors.toSet());

        ItemEstoquePrateleiraCommand prateleiraCommand = new ItemEstoquePrateleiraCommand(dto.getPlateleira().getIdPrateleira());

        ItemEstoqueImagemCommand imagemCommand = new ItemEstoqueImagemCommand(dto.getImagem().getIdImagem(), dto.getImagem().getUrl());

        return new ItemEstoqueCadastrarCommand(
                dto.getDescricao(),
                dto.getComplemento(),
                dto.getPeso(),
                dto.getQtdMinimo(),
                dto.getQtdArmazenado(),
                dto.getNotificar(),
                categoria,
                caracteristicasDto,
                prateleiraCommand,
                dto.getPreco(),
                imagemCommand
        );
    }

    public static ItemEstoqueAtualizarPorIdCommand toAtualizarPorIdCommand(Integer id, ItemEstoqueRequestDto dto) {

        ItemEstoqueCategoriaCommand categoria = new ItemEstoqueCategoriaCommand(dto.getCategoria().getIdCategoria());

        Set<ItemEstoqueCategoriaCommand> caracteristicasDto = dto.getCaracteristicas()
                .stream()
                .map(caracteristica -> new ItemEstoqueCategoriaCommand(caracteristica.getIdCategoria()))
                .collect(Collectors.toSet());

        ItemEstoquePrateleiraCommand prateleiraCommand = new ItemEstoquePrateleiraCommand(dto.getPlateleira().getIdPrateleira());

        ItemEstoqueImagemCommand imagemCommand = new ItemEstoqueImagemCommand(dto.getImagem().getIdImagem(), dto.getImagem().getUrl());

        return new ItemEstoqueAtualizarPorIdCommand(
                id,
                dto.getDescricao(),
                dto.getComplemento(),
                dto.getPeso(),
                dto.getQtdMinimo(),
                dto.getQtdArmazenado(),
                dto.getNotificar(),
                categoria,
                caracteristicasDto,
                prateleiraCommand,
                dto.getPreco(),
                imagemCommand
        );
    }

    public static ItemEstoqueAtualizarPorIdCommand toAtualizarPorIdCommand(ItemEstoque domain) {

        ItemEstoqueCategoriaCommand categoria = new ItemEstoqueCategoriaCommand(domain.getCategoria().getIdCategoria());

        Set<ItemEstoqueCategoriaCommand> caracteristicasDto = domain.getCaracteristicas()
                .stream()
                .map(caracteristica -> new ItemEstoqueCategoriaCommand(caracteristica.getIdCategoria()))
                .collect(Collectors.toSet());

        ItemEstoquePrateleiraCommand prateleiraCommand = new ItemEstoquePrateleiraCommand(domain.getPrateleira().getIdPrateleira());

        ItemEstoqueImagemCommand imagemCommand = new ItemEstoqueImagemCommand(domain.getImagem().getIdImagem(), domain.getImagem().getUrl());

        return new ItemEstoqueAtualizarPorIdCommand(
                domain.getIdItemEstoque(),
                domain.getDescricao(),
                domain.getComplemento(),
                domain.getPeso(),
                domain.getQtdMinimo(),
                domain.getQtdArmazenado(),
                domain.getNotificar(),
                categoria,
                caracteristicasDto,
                prateleiraCommand,
                domain.getPreco(),
                imagemCommand
        );
    }

    public static ItemEstoqueCadastrarTecidoRoupaCommand toCadastrarTecidoRoupaCommand(Integer id, Set<ConfeccaoRoupaEntity> confeccaoRoupaEntity) {
        Set<ConfeccaoRoupa> confeccaoRoupaDomain;

        confeccaoRoupaDomain = confeccaoRoupaEntity.stream()
                .map(confeccao -> {
                    ItemEstoque roupa = new ItemEstoque(confeccao.getRoupa().getIdItemEstoque());
                    ItemEstoque tecido = new ItemEstoque(confeccao.getTecido().getIdItemEstoque());
                    return new ConfeccaoRoupa(confeccao.getIdConfeccaoRoupa(), roupa, tecido, confeccao.getQtdTecido());
                })
                .collect(Collectors.toSet());

        return new ItemEstoqueCadastrarTecidoRoupaCommand(id, confeccaoRoupaDomain);
    }

    // --- Domain -> Response DTO (para quando você recebe Domain diretamente)
    public static ItemEstoqueResponseDto toResponseDto(ItemEstoque item) {
        if (item == null) return null;

        ItemEstoqueCategoriaPaiResponseDto categoriaPaiDto = null;
        ItemEstoqueCategoriaResponseDto categoriaDto = null;

        System.out.println("Metodo de toResponseDto :" + item.getCategoria().getNome());
        if (item.getCategoria() != null) {
            if (item.getCategoria().getCategoriaPai() != null) {
                categoriaPaiDto = new ItemEstoqueCategoriaPaiResponseDto(item.getCategoria().getCategoriaPai().getNome());
            }
            categoriaDto = new ItemEstoqueCategoriaResponseDto(item.getCategoria().getIdCategoria(), item.getCategoria().getNome(), categoriaPaiDto);
        }

        Set<ItemEstoqueCaracteristicaResponseDto> caracteristicasDto = null;
        if (item.getCaracteristicas() != null) {
            caracteristicasDto = item.getCaracteristicas().stream()
                    .filter(Objects::nonNull)
                    .map(c -> new ItemEstoqueCaracteristicaResponseDto(c.getIdCategoria(), c.getNome()))
                    .collect(Collectors.toSet());
        }

        Set<ItemEstoqueConfeccaoRoupaDto> confeccaoRoupaDto = null;
        if (item.getConfeccaoRoupa() != null) {
            confeccaoRoupaDto = item.getConfeccaoRoupa().stream()
                    .filter(Objects::nonNull)
                    .map(cr -> new ItemEstoqueConfeccaoRoupaDto(
                            cr.getIdConfeccaoRoupa(),
                            cr.getTecido() != null ? new ItemEstoqueTecidoDto(cr.getTecido().getIdItemEstoque(), cr.getTecido().getDescricao()) : null,
                            cr.getQtdTecido()
                    ))
                    .collect(Collectors.toSet());
        }

        ItemEstoquePrateleiraResponseDto prateleiraDto = item.getPrateleira() != null
                ? new ItemEstoquePrateleiraResponseDto(item.getPrateleira().getIdPrateleira(), item.getPrateleira().getCodigo())
                : null;

        ItemEstoqueImagemResponseDto imagemDto = null;
        if (item.getImagem() != null) {
            imagemDto = new ItemEstoqueImagemResponseDto(item.getImagem().getIdImagem(), item.getImagem().getUrl());
        }

        return new ItemEstoqueResponseDto(
                item.getIdItemEstoque(),
                item.getDescricao(),
                item.getPeso(),
                item.getQtdMinimo(),
                item.getQtdArmazenado(),
                item.getComplemento(),
                item.getNotificar(),
                categoriaDto,
                caracteristicasDto,
                confeccaoRoupaDto,
                prateleiraDto,
                item.getPreco(),
                imagemDto
        );
    }

    public static ItemEstoqueResponseCadastroDto toResponseCadastroDto(ItemEstoque item) {
        if (item == null) return null;

        Integer idCategoria = null;

        if (item.getCategoria() != null) {
            idCategoria = item.getCategoria().getIdCategoria();
        }

        Set<Integer> nomeCaracteristicas = null;
        if (item.getCaracteristicas() != null) {
            nomeCaracteristicas = item.getCaracteristicas().stream()
                    .filter(Objects::nonNull)
                    .map(Categoria::getIdCategoria)
                    .collect(Collectors.toSet());
        }

        Integer idPrateleira = null;
        if (item.getPrateleira() != null){
            idPrateleira = item.getPrateleira().getIdPrateleira();
        }


        Integer idImagem = null;
        if (item.getImagem() != null) {
            idImagem = item.getImagem().getIdImagem();
        }

        return new ItemEstoqueResponseCadastroDto(
                item.getIdItemEstoque(),
                item.getDescricao(),
                item.getPeso(),
                item.getQtdMinimo(),
                item.getQtdArmazenado(),
                item.getNotificar(),
                idCategoria,
                nomeCaracteristicas,
                idPrateleira,
                item.getPreco(),
                idImagem
        );
    }

    public static ItemEstoqueResumidoDto toItemEstoqueResumido(ItemEstoque itemEstoque){
        String tipoCategoria = null;
        if (itemEstoque.getCategoria().getCategoriaPai() != null){
            Integer idCategoriaPai = itemEstoque.getCategoria().getCategoriaPai().getIdCategoria();
            tipoCategoria = (idCategoriaPai == 1) ? "tecido" : "roupa";
        }
        return new ItemEstoqueResumidoDto(
                itemEstoque.getIdItemEstoque(),
                itemEstoque.getDescricao(),
                tipoCategoria,
                itemEstoque.getPreco(),
                itemEstoque.getImagem().getUrl()
        );
    }

    public static List<ItemEstoqueResumidoDto> toItensEstoqueResumidos(List<ItemEstoque> itensEstoque) {
        return itensEstoque
                .stream()
                .map(ItemEstoqueEntityMapper::toItemEstoqueResumido)
                .toList();
    }
}
