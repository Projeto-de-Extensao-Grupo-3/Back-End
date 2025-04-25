package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.permissao.PermissaoMapper;
import school.sptech.CRUDBackend.dto.permissao.PermissaoResponseDto;
import school.sptech.CRUDBackend.service.PermissaoService;

import java.util.List;


@Tag(name = "Permissão", description = "Operações CRUD relacionadas ás permissões de funcionários.")
@RestController
@RequestMapping("/permissoes")
@RequiredArgsConstructor
public class PermissaoController {
    private final PermissaoService permissaoService;

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
}
