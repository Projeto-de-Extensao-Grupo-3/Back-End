package school.sptech.CleanArchitecture.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CleanArchitecture.core.application.command.categoria.CategoriaAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.command.categoria.CriarCategoriaCommand;
import school.sptech.CleanArchitecture.core.application.usecase.categoria.*;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntityMapper;
import school.sptech.CleanArchitecture.core.application.mapper.CategoriaMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.categoria.CategoriaRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.categoria.CategoriaResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Categoria Controller", description = "Operações CRUD relacionadas as categorias de item.")
@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaAtualizarPorIdUseCase categoriaAtualizarPorIdUseCase;

    private final CategoriaBuscarPorIdUseCase categoriaBuscarPorIdUseCase;

    private final CategoriaBuscarPorNomeUseCase categoriaBuscarPorNomeUseCase;

    private final CategoriaListAllUseCase categoriaListAllUseCase;

    private final CategoriaListarPorTipoUseCase categoriaListarPorTipoUseCase;

    private final CategoriaRemoverPorId categoriaRemoverPorId;

    private final CriarCategoriaUseCase criarCategoriaUseCase;

    @Operation(
            summary = "Cadastro de uma nova Categoria de Item.",
            description = "Retorna um objeto do tipo CategoriaResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso."),
            @ApiResponse(responseCode = "409", description = "O nome de Categoria informada já existe no sistema.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo CategoriaRequestDto para cadastro.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<CategoriaResponseDto> cadastrar(@RequestBody CategoriaRequestDto categoriaParaCadastrar) {
        CriarCategoriaCommand command = CategoriaEntityMapper.toCriarCommand(categoriaParaCadastrar);
        Categoria categoria = criarCategoriaUseCase.executar(command);
        CategoriaEntity entity = CategoriaEntityMapper.ofDomain(categoria);
        CategoriaResponseDto categoriaCadastrada = CategoriaEntityMapper.toResponseDto(entity);
        return ResponseEntity.status(201).body(categoriaCadastrada);
    }

    @Operation(summary = "Listagem de todas as Categorias.", description = "Retorna uma lista de CategoriaResponseDto com todas as Categorias no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Categorias."),
            @ApiResponse(responseCode = "204", description = "Lista de Categorias está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listarTodas() {
        List<CategoriaEntity> todasCategorias = categoriaListAllUseCase.executar()
                .stream()
                .map(CategoriaEntityMapper::ofDomain)
                .collect(Collectors.toList());

        List<CategoriaResponseDto> response = todasCategorias
                .stream()
                .map(CategoriaEntityMapper::toResponseDto)
                .collect(Collectors.toList());

        return response.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Listagem de todas as Categorias por tipo (roupa, tecido ou característica.", description = "Retorna uma lista de CategoriaResponseDto com todas as Categorias no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Categorias."),
            @ApiResponse(responseCode = "204", description = "Lista de Categorias está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CategoriaResponseDto>> listarCategoriasPorTipo(@PathVariable String tipo) {
        List<CategoriaEntity> todasCategorias = categoriaListarPorTipoUseCase.execute(tipo)
                .stream()
                .map(CategoriaEntityMapper::ofDomain)
                .collect(Collectors.toList());

        List<CategoriaResponseDto> response = todasCategorias
                .stream()
                .map(CategoriaEntityMapper::toResponseDto)
                .collect(Collectors.toList());

        return response.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Exibição de uma Categoria por ID", description = "Retorna um objeto CategoriaResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> buscarPorId(@PathVariable Integer id) {
        CategoriaEntity categoriaEncontrada = CategoriaEntityMapper.ofDomain(
                categoriaBuscarPorIdUseCase.executar(id));
        CategoriaResponseDto response = CategoriaEntityMapper.toResponseDto(categoriaEncontrada);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Exibição de uma Categoria por nome", description = "Retorna um objeto CategoriaResponseDto de acordo com o nome informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o nome passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{nome}")
    public ResponseEntity<CategoriaResponseDto> buscarPorNome(@PathVariable String nome) {
        CategoriaEntity categoriaEncontrada = CategoriaEntityMapper.ofDomain(
                categoriaBuscarPorNomeUseCase.execute(nome));
        CategoriaResponseDto response = CategoriaEntityMapper.toResponseDto(categoriaEncontrada);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Atualização de Categorias.", description = "Retorna um objeto CategoriaResponseDto atualizado com os valores de um CategoriaRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo CategoriaRequestDto com valores de atualização.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid CategoriaRequestDto categoriaAtualizar){
        CategoriaAtualizarCommand command = CategoriaEntityMapper.toAtualzarCommand(id, categoriaAtualizar);
        CategoriaEntity categoriaEncontrada = CategoriaEntityMapper.ofDomain(
                categoriaAtualizarPorIdUseCase.execute(command));
        CategoriaResponseDto response = CategoriaEntityMapper.toResponseDto(categoriaEncontrada);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Deleção de um registro de Categoria.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        categoriaRemoverPorId.execute(id);
        return ResponseEntity.status(200).build();
    }
}
