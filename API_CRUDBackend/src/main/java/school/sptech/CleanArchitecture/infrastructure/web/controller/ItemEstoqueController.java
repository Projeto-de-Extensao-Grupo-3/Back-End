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
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa.ConfeccaoRoupaCadastrarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueCalcularCustoProducaoUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa.ConfeccaoRoupaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa.ConfeccaoRoupaEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Tag(name = "* Item de Estoque Controller", description = "Operações CRUD relacionadas aos itens de estoque (tecido ou roupa).")
@RestController
@RequestMapping("/itens-estoque")
@RequiredArgsConstructor
public class ItemEstoqueController {

    private final ItemEstoqueAtualizarPorIdUseCase itemEstoqueAtualizarPorIdUseCase;

    private final ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarPorIdUseCase;

    private final ItemEstoqueCadastrarItemUseCase itemEstoqueCadastrarItemUseCase;

    private final ItemEstoqueListAllUseCase itemEstoqueListAllUseCase;

    private final ItemEstoqueRemoverPorIdUseCase itemEstoqueRemoverPorIdUseCase;

    private final ItemEstoqueBuscarPorDescricaoUseCase itemEstoqueBuscarPorDescricaoUseCase;

    private final ItemEstoqueBuscarPorTipoUseCase itemEstoqueBuscarPorTipoUseCase;

    private final ItemEstoqueCalcularCustoProducaoUseCase itemEstoqueCalcularCustoProducaoUseCase;

    private final ConfeccaoRoupaCadastrarUseCase confeccaoRoupaCadastrarUseCase;

    @Operation(
            summary = "Cadastramento de um novo Item.",
            description = "Retorna um objeto do tipo ItemEstoqueResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item de Estoque cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "A descrição de Item informada já existe no sistema.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ItemEstoqueRequestDto para cadastro.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<ItemEstoqueResponseCadastroDto> cadastrar(
            @RequestBody @Valid ItemEstoqueRequestDto itemEstoqueCadastrar
    ) {
        ItemEstoqueCadastrarCommand command = ItemEstoqueEntityMapper.toCadastrarCommand(itemEstoqueCadastrar);
        ItemEstoqueResponseCadastroDto itemCadastrado = ItemEstoqueEntityMapper.toResponseCadastroDto(
                itemEstoqueCadastrarItemUseCase.execute(command));
        return ResponseEntity.status(201).body(itemCadastrado);
    }

    @SecurityRequirement(name = "Bearer")
    @PostMapping("/tecidos/{id}")
    public ResponseEntity<Void> cadastrarTecidosDaRoupa(
            @PathVariable Integer id,
            @RequestBody Set<ItemEstoqueConfeccaoRoupaDto> confeccaoRoupaDto
    ) {
        Set<ConfeccaoRoupaEntity> confeccaoRoupa = new HashSet<>();
        for (ItemEstoqueConfeccaoRoupaDto confeccaoDto : confeccaoRoupaDto) {
            ConfeccaoRoupaEntity confeccao = new ConfeccaoRoupaEntity();
            ItemEstoqueEntity tecido = new ItemEstoqueEntity();
            tecido.setIdItemEstoque(confeccaoDto.getTecido().getIdTecido());
            ItemEstoqueEntity roupa = new ItemEstoqueEntity();
            roupa.setIdItemEstoque(id);
            confeccao.setTecido(tecido);
            confeccao.setRoupa(roupa);
            confeccao.setQtdTecido(confeccaoDto.getQtdTecido());
            confeccaoRoupa.add(confeccao);
        }

        Set<ConfeccaoRoupaCadastrarCommand> command = ConfeccaoRoupaEntityMapper.toCadastrarCommands(confeccaoRoupa);
        confeccaoRoupaCadastrarUseCase.execute(id, command);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "* Listagem de todos os Itens no Estoque.", description = "Retorna uma lista de ItemEstoqueResponseDto com todos os Itens no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui itens de estoque."),
            @ApiResponse(responseCode = "204", description = "Lista de itens está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<ItemEstoqueResponseDto>> verificarTodos() {
        List<ItemEstoqueResponseDto> todosItens = ItemEstoqueEntityMapper.toResponseDtosEntity(
                itemEstoqueListAllUseCase.execute()
        );
        if(todosItens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(todosItens);
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/itensResumidos")
    public ResponseEntity<List<ItemEstoqueResumidoDto>> listAllResumido(){
        List<ItemEstoqueResumidoDto> todosItens = ItemEstoqueEntityMapper.toItensEstoqueResumidos(
                itemEstoqueListAllUseCase.execute()
        );
        if(todosItens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(todosItens);
    }

    @Operation(summary = "Exibição de um item por ID", description = "Retorna um objeto ItemEstoqueResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<ItemEstoqueResponseDto> buscarPorId(@PathVariable Integer id) {
        ItemEstoqueResponseDto itemEstoque = ItemEstoqueEntityMapper.toResponseDto(
                itemEstoqueBuscarPorIdUseCase.execute(id)
        );
        return ResponseEntity.status(200).body(itemEstoque);
    }

    @Operation(summary = "Atualização de Item.", description = "Retorna um objeto ItemEstoqueResponseDto atualizado com os valores de um ItemEstoqueRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ItemEstoqueRequestDto com valores de atualização.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<ItemEstoqueResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid ItemEstoqueRequestDto itemEstoqueAtualizar
    ) {
        ItemEstoqueAtualizarPorIdCommand command = ItemEstoqueEntityMapper.toAtualizarPorIdCommand(id, itemEstoqueAtualizar);
        ItemEstoqueResponseDto itemCadastrado = ItemEstoqueEntityMapper.toResponseDto(
                itemEstoqueAtualizarPorIdUseCase.execute(command));
        return ResponseEntity.status(201).body(itemCadastrado);
    }

    @Operation(summary = "Deleção de um registro de Item.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(
            @PathVariable Integer id
    ) {
        itemEstoqueRemoverPorIdUseCase.execute(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{tipo}/filtros")
    public ResponseEntity<List<ItemEstoqueResponseDto>> buscarPorDescricao(@PathVariable String tipo, @RequestParam String descricao) {
        List<ItemEstoqueResponseDto> itens = ItemEstoqueEntityMapper.toResponseDtosEntity(
               itemEstoqueBuscarPorDescricaoUseCase.execute(descricao, tipo)
        );
        if(itens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(itens);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<ItemEstoqueResponseDto>> buscarPorTipo(@RequestParam String tipo) {
        List<ItemEstoqueResponseDto> itens = ItemEstoqueEntityMapper.toResponseDtosEntity(
                itemEstoqueBuscarPorTipoUseCase.execute(tipo)
        );
        if(itens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(itens);
    }

    @GetMapping("/custos/{id}")
    public ResponseEntity<Double> calcularCustoProducao(@PathVariable Integer id) {
        Double custo = itemEstoqueCalcularCustoProducaoUseCase.execute(id);
        return ResponseEntity.status(200).body(custo);
    }
}

