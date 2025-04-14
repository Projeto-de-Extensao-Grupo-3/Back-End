package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.corteTecido.CorteTecidoMapper;
import school.sptech.CRUDBackend.dto.corteTecido.CorteTecidoRequestDto;
import school.sptech.CRUDBackend.dto.corteTecido.CorteTecidoResponseDto;
import school.sptech.CRUDBackend.entity.CorteTecido;
import school.sptech.CRUDBackend.service.CorteTecidoService;

import java.util.List;

@Tag(name = "Cortes de Tecidos", description = "Operações CRUD relacionadas aos cortes de tecidos (data de inicio, término do corte e quem efetuou).")
@RestController
@RequestMapping("/cortes-tecido")
@RequiredArgsConstructor
public class CorteTecidoController {
    private final CorteTecidoService corteTecidoService;

    @Operation(
            summary = "Cadastramento de um novo corte de tecido.",
            description = "Retorna um objeto do tipo CorteTecidoResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Corte cadastrado com sucesso.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo CorteTecidoRequestDto para cadastro.",
            required = true
    )
    @PostMapping
    public ResponseEntity<CorteTecidoResponseDto> cadastrar(
            @RequestBody @Valid CorteTecidoRequestDto corteTecido
    ) {
        CorteTecido corteParaCadastrar = CorteTecidoMapper.toEntity(corteTecido);
        CorteTecidoResponseDto corteTecidoCadastrado = CorteTecidoMapper.toResponseDto(
                corteTecidoService.cadastrarCorteTecido(corteParaCadastrar)
        );
        return ResponseEntity.status(201).body(corteTecidoCadastrado);
    }


    @Operation(summary = "Listagem de todos os cortes de tecidos no sistema.", description = "Retorna uma lista de CorteTecidoResponseDtoa com todos os cortes de tecido no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "lista possui cortes de tecido"),
            @ApiResponse(responseCode = "204", description = "lista de cortes está vazia"),
    })
    @GetMapping
    public ResponseEntity<List<CorteTecidoResponseDto>> listarTodos() {
        List<CorteTecidoResponseDto> cortesTecido = CorteTecidoMapper.toResponseDtos(
                corteTecidoService.verificarTodosCortesTecido()
        );
        if (cortesTecido.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cortesTecido);
    }


    @Operation(summary = "Exibição de um corte de tecido por ID", description = "Retorna um objeto CorteTecidoResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID informado no PathVariable foi encontrado."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<CorteTecidoResponseDto> buscarPorId(@PathVariable Integer id) {
        CorteTecidoResponseDto corteTecido = CorteTecidoMapper.toResponseDto(
                corteTecidoService.buscarCorteTecidoPorId(id)
        );
        return ResponseEntity.status(200).body(corteTecido);
    }

    @Operation(summary = "Atualização de Corte de Tecido.", description = "Retorna um objeto CorteTecidoResponseDto atualizado com os valores de um CorteTecidoRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID informado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo CorteTecidoRequestDto com valores de atualização.",
            required = true
    )
    @PutMapping("/{id}")
    public ResponseEntity<CorteTecidoResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid CorteTecidoRequestDto corteTecido
    ) {
        CorteTecido corteParaAtualizar = CorteTecidoMapper.toEntity(corteTecido);
        CorteTecidoResponseDto corteAtualizado = CorteTecidoMapper.toResponseDto(
                corteTecidoService.atualizarCorteTecido(id, corteParaAtualizar)
        );
        return ResponseEntity.status(200).body(corteAtualizado);
    }

    @Operation(summary = "Deleção de um registro de corte de tecido.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID informado no PathVariable foi encontrado."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        corteTecidoService.deletarCorteTecido(id);
        return ResponseEntity.status(204).build();
    }
}
