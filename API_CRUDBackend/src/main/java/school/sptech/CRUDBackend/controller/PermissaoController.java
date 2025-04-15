package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.permissao.PermissaoMapper;
import school.sptech.CRUDBackend.dto.permissao.PermissaoRequestDto;
import school.sptech.CRUDBackend.dto.permissao.PermissaoResponseDto;
import school.sptech.CRUDBackend.entity.Permissao;
import school.sptech.CRUDBackend.service.PermissaoService;

import java.util.List;


@Tag(name = "Permissão", description = "Operações CRUD relacionadas ás permissões de funcionários.")
@RestController
@RequestMapping("/permissoes")
@RequiredArgsConstructor
public class PermissaoController {
    private final PermissaoService permissaoService;

    @Operation(
            summary = "Cadastramento de uma novo permissão.",
            description = "Retorna um objeto do tipo PermissaoResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Corte cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "A descrição de Permissão informada já existe no sistema.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo PermissaoRequestDto para cadastro.",
            required = true
    )
    @PostMapping
    public ResponseEntity<PermissaoResponseDto> cadastrar(
            @RequestBody @Valid PermissaoRequestDto permissao
    ) {
        Permissao permissaoParaCadastrar = PermissaoMapper.toEntity(permissao);
        PermissaoResponseDto permissaoCadastrada = PermissaoMapper.toResponseDto(
                permissaoService.cadastrarPermissao(permissaoParaCadastrar)
        );
        return ResponseEntity.status(201).body(permissaoCadastrada);
    }

    @Operation(summary = "Listagem de todos as permissões.", description = "Retorna uma lista de PermissaoResponseDto com todos as Permissões no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui permissões."),
            @ApiResponse(responseCode = "204", description = "Lista de permissões está vazia"),
    })
    @GetMapping
    public ResponseEntity<List<PermissaoResponseDto>> listarTodos() {
        List<PermissaoResponseDto> permissoes = PermissaoMapper.toResponseDtos(
                permissaoService.verificarTodasPermissoes()
        );
        if (permissoes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(permissoes);
    }

    @Operation(summary = "Atualização de Permissão.", description = "Retorna um objeto PermissaoResponseDto atualizado com os valores de um PermissaoRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo PermissaoRequestDto com valores de atualização.",
            required = true
    )
    @PutMapping("/{id}")
    public ResponseEntity<PermissaoResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid PermissaoRequestDto permissaoParaAtualizar
    ) {
        Permissao permissao = PermissaoMapper.toEntity(permissaoParaAtualizar);
        PermissaoResponseDto permissaoAtualizada = PermissaoMapper.toResponseDto(
                permissaoService.atualizarPermissao(id, permissao)
        );
        return ResponseEntity.status(200).body(permissaoAtualizada);
    }

    @Operation(summary = "Deleção de um registro de Permissão.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        permissaoService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
