package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.servicoTerceiro.ServicoTerceiroMapper;
import school.sptech.CRUDBackend.dto.servicoTerceiro.ServicoTerceiroRequestDto;
import school.sptech.CRUDBackend.dto.servicoTerceiro.ServicoTerceiroResponseDto;
import school.sptech.CRUDBackend.entity.ServicoTerceiro;
import school.sptech.CRUDBackend.service.ServicoTerceiroService;

import java.util.List;

@Tag(name = "Serviço Terceiro Controller", description = "Operações CRUD relacionadas aos fornecedores ou costureiras que atuam como serviços terceiro.")
@RestController
@RequestMapping("/servico-terceiros")
@RequiredArgsConstructor
public class ServicoTerceiroController {
    private final ServicoTerceiroService servicoTerceiroService;

    @Operation(
            summary = "Cadastro de um novo Provedor de Serviço.",
            description = "Retorna um objeto do tipo ServicoTerceiroResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "O Email, identificação ou endereço do Serviço informada já existe no sistema.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ServicoTerceiroRequestDto para cadastro.",
            required = true
    )
    @PostMapping
    public ResponseEntity<ServicoTerceiroResponseDto> cadastrar(
            @RequestBody @Valid ServicoTerceiroRequestDto servicoTerceiroCad
    ) {
        ServicoTerceiro servicoParaCadastrar = ServicoTerceiroMapper.toEntity(servicoTerceiroCad);
        ServicoTerceiroResponseDto novoServicoTerceiro = ServicoTerceiroMapper.toResponseDto(
                servicoTerceiroService.cadastrarServicoTerceiro(servicoParaCadastrar)
        );
        return ResponseEntity.status(201).body(novoServicoTerceiro);
    }

    @Operation(summary = "Listagem de todos os Serviços.", description = "Retorna uma lista de ServicoTerceiroResponseDto com todos os Serviços no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui Serviços."),
            @ApiResponse(responseCode = "204", description = "Lista de Serviços está vazia"),
    })
    @GetMapping
    public ResponseEntity<List<ServicoTerceiroResponseDto>> verificarTodos() {
        List<ServicoTerceiroResponseDto> servicosTerceiro = ServicoTerceiroMapper.toResponseDtos(
                servicoTerceiroService.verificarTodosServicosTerceiro()
        );
        if (servicosTerceiro.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(servicosTerceiro);
    }


    @Operation(summary = "Exibição de um Serviços por ID", description = "Retorna um objeto ServicoTerceiroResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ServicoTerceiroResponseDto> servicoTerceiroPorId (@PathVariable Integer id) {
        ServicoTerceiroResponseDto servicoTerceiroPorId = ServicoTerceiroMapper.toResponseDto(
                servicoTerceiroService.buscarServicoTerceiroPorId(id)
        );
        return ResponseEntity.status(200).body(servicoTerceiroPorId);
    }

    @Operation(summary = "Atualização de Serviços.", description = "Retorna um objeto ServicoTerceiroResponseDto atualizado com os valores de um ServicoTerceiroRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ServicoTerceiroRequestDto com valores de atualização.",
            required = true
    )
    @PutMapping("/{id}")
    public ResponseEntity<ServicoTerceiroResponseDto> atualizarPorId (
            @PathVariable Integer id,
            @RequestBody @Valid ServicoTerceiroRequestDto servicoTerceiroAtualizar
    ) {
        ServicoTerceiro servicoParaAtualizar = ServicoTerceiroMapper.toEntity(servicoTerceiroAtualizar);
        ServicoTerceiroResponseDto servicoAtualizado = ServicoTerceiroMapper.toResponseDto(
                servicoTerceiroService.atualizarServicoTerceiroPorId(id, servicoParaAtualizar)
        );
        return ResponseEntity.status(200).body(servicoAtualizado);
    }

    @Operation(summary = "Deleção de um registro de Serviço.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        servicoTerceiroService.removerServicoTerceiroPorId(id);
        return ResponseEntity.status(204).build();
    }
}
