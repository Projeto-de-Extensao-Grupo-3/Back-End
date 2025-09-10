package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.parceiro.ParceiroMapper;
import school.sptech.CRUDBackend.dto.parceiro.ParceiroRequestDto;
import school.sptech.CRUDBackend.dto.parceiro.ParceiroResponseDto;
import school.sptech.CRUDBackend.entity.Parceiroaa;
import school.sptech.CRUDBackend.service.ParceiroService;

import java.util.List;

@Tag(name = "Serviço Terceiro Controller", description = "Operações CRUD relacionadas aos fornecedores ou costureiras que atuam como serviços terceiro.")
@RestController
@RequestMapping("/parceiros")
@RequiredArgsConstructor
public class ParceiroController {
    private final ParceiroService parceiroService;

    @Operation(
            summary = "Cadastro de um novo Provedor de Serviço.",
            description = "Retorna um objeto do tipo ParceiroResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "O Email, identificação ou endereço do Serviço informada já existe no sistema.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ParceiroRequestDto para cadastro.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<ParceiroResponseDto> cadastrar(
            @RequestBody @Valid ParceiroRequestDto servicoTerceiroCad
    ) {
        Parceiroaa servicoParaCadastrar = ParceiroMapper.toEntity(servicoTerceiroCad);
        ParceiroResponseDto novoParceiro = ParceiroMapper.toResponseDto(
                parceiroService.cadastrarParceiro(servicoParaCadastrar)
        );
        return ResponseEntity.status(201).body(novoParceiro);
    }

    @Operation(summary = "Listagem de todos os Serviços.", description = "Retorna uma lista de ParceiroResponseDto com todos os Serviços no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui Serviços."),
            @ApiResponse(responseCode = "204", description = "Lista de Serviços está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/listagem/{categoria}")
    public ResponseEntity<List<ParceiroResponseDto>> verificarTodos(@PathVariable String categoria) {
        List<ParceiroResponseDto> parceiro = ParceiroMapper.toResponseDtos(
                parceiroService.verificarTodosParceiros(categoria)
        );
        if (parceiro.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(parceiro);
    }


    @Operation(summary = "Exibição de um Serviços por ID", description = "Retorna um objeto ParceiroResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{categoria}/nome")
    public ResponseEntity<List<ParceiroResponseDto>> servicoTerceiroPorNome(
            @PathVariable String categoria, @RequestParam String nome
    ) {
        List<ParceiroResponseDto> parceiro = ParceiroMapper.toResponseDtos(
                parceiroService.buscarParceiroPorNome(categoria, nome)
        );
        if (parceiro.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(parceiro);
    }

    @Operation(summary = "Atualização de Serviços.", description = "Retorna um objeto ParceiroResponseDto atualizado com os valores de um ParceiroRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ParceiroRequestDto com valores de atualização.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<ParceiroResponseDto> atualizarPorId (
            @PathVariable Integer id,
            @RequestBody @Valid ParceiroRequestDto servicoTerceiroAtualizar
    ) {
        Parceiroaa servicoParaAtualizar = ParceiroMapper.toEntity(servicoTerceiroAtualizar);
        ParceiroResponseDto servicoAtualizado = ParceiroMapper.toResponseDto(
                parceiroService.atualizarParceiroPorId(id, servicoParaAtualizar)
        );
        return ResponseEntity.status(200).body(servicoAtualizado);
    }

    @Operation(summary = "Deleção de um registro de Serviço.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        parceiroService.removerParceiroPorId(id);
        return ResponseEntity.status(204).build();
    }
}
