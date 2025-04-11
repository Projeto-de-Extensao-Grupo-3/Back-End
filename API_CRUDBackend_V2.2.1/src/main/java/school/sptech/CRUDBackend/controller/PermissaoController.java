package school.sptech.CRUDBackend.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.permissao.PermissaoMapper;
import school.sptech.CRUDBackend.dto.permissao.PermissaoRequestDto;
import school.sptech.CRUDBackend.dto.permissao.PermissaoResponseDto;
import school.sptech.CRUDBackend.entity.Permissao;
import school.sptech.CRUDBackend.service.PermissaoService;

import java.util.List;

@RestController
@RequestMapping("/permissoes")
@RequiredArgsConstructor
public class PermissaoController {
    private final PermissaoService permissaoService;

    @PostMapping
    public ResponseEntity<PermissaoResponseDto> cadastrarPermissao(
            @RequestBody @Valid PermissaoRequestDto permissao) {
        Permissao permissaoParaCadastrar = PermissaoMapper.toEntity(permissao);
        PermissaoResponseDto permissaoCadastrada = PermissaoMapper.toResponseDto(
                permissaoService.cadastrar(permissaoParaCadastrar));
        return ResponseEntity.status(201).body(permissaoCadastrada);
    }

    @GetMapping
    public ResponseEntity<List<PermissaoResponseDto>> listarTodos() {
        List<PermissaoResponseDto> permissoes = PermissaoMapper.toResponseDtos(permissaoService.listar());
        return permissoes.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(permissoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissaoResponseDto> atualizarPermissao(
            @PathVariable Integer id,
            @RequestBody PermissaoRequestDto permissaoParaAtualizar
    ) {
        Permissao permissao = PermissaoMapper.toEntity(permissaoParaAtualizar);
        PermissaoResponseDto permissaoAtualizada = PermissaoMapper.toResponseDto(
                permissaoService.atualizar(id, permissao)
        );
        return ResponseEntity.status(200).body(permissaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPermissao(@PathVariable Integer id) {
        permissaoService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
