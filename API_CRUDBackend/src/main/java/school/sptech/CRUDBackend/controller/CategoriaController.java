package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.Categoria.CategoriaMapper;
import school.sptech.CRUDBackend.dto.Categoria.CategoriaRequestDto;
import school.sptech.CRUDBackend.dto.Categoria.CategoriaResponseDto;
import school.sptech.CRUDBackend.entity.Categoria;
import school.sptech.CRUDBackend.service.CategoriaService;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Categoria Controller", description = "Operações CRUD relacionadas as categorias de item.")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }


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
        Categoria categoria = CategoriaMapper.toEntity(categoriaParaCadastrar);
        CategoriaResponseDto categoriaCadastrada = CategoriaMapper.toResponseDto(service.cadastrarCategoria(categoria));
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
       List<CategoriaResponseDto> todasCategorias = service.listarTodos()
               .stream()
               .map(CategoriaMapper::toResponseDto)
               .collect(Collectors.toList());

       if (todasCategorias.isEmpty()) {
           return ResponseEntity.status(204).build();
       }

       return ResponseEntity.status(200).body(todasCategorias);
   }

    @Operation(summary = "Exibição de uma Categoria por ID", description = "Retorna um objeto CategoriaResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> buscarPorId(@PathVariable Integer id) {
        CategoriaResponseDto categoriaEncontrada = CategoriaMapper.toResponseDto(service.buscarPorId(id));
        return ResponseEntity.status(200).body(categoriaEncontrada);
    }

    @Operation(summary = "Exibição de uma Categoria por nome", description = "Retorna um objeto CategoriaResponseDto de acordo com o nome informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o nome passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{nome}")
    public ResponseEntity<CategoriaResponseDto> buscarPorNome(@PathVariable String nome) {
        CategoriaResponseDto categoriaEncontrada = CategoriaMapper.toResponseDto(service.buscarPorNome(nome));
        return ResponseEntity.status(200).body(categoriaEncontrada);
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
        Categoria categoriaParaAtualizar = CategoriaMapper.toEntity(categoriaAtualizar);
        CategoriaResponseDto categoriaAtualizada = CategoriaMapper.toResponseDto(service.atualizarPorId(id, categoriaParaAtualizar));
        return ResponseEntity.status(200).body(categoriaAtualizada);
    }

    @Operation(summary = "Deleção de um registro de Categoria.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        service.removerPorId(id);
        return ResponseEntity.status(200).build();
    }
}
