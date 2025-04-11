package school.sptech.CRUDBackend.controller;

import jakarta.validation.Valid;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.funcionario.FuncionarioMapper;
import school.sptech.CRUDBackend.dto.funcionario.FuncionarioRequestDto;
import school.sptech.CRUDBackend.dto.funcionario.FuncionarioResponseDto;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@Setter
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDto> cadastrar(
            @RequestBody @Valid FuncionarioRequestDto funcionarioCad
    ) {
        Funcionario funcionarioParaCadastrar = FuncionarioMapper.toEntity(funcionarioCad);
        FuncionarioResponseDto funcionarioCadastrado = FuncionarioMapper.toResponseDto(
                funcionarioService.cadastrarFuncionario(funcionarioParaCadastrar)
        );
        return ResponseEntity.status(201).body(funcionarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDto>> verificarTodos() {
        List<FuncionarioResponseDto> todosFuncionarios = FuncionarioMapper.toResponseDtos(
                funcionarioService.verificarTodosFuncionarios()
        );
        if(todosFuncionarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(todosFuncionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> buscarPorId(@PathVariable Integer id) {
        FuncionarioResponseDto funcionario = FuncionarioMapper.toResponseDto(
                funcionarioService.buscarFuncionarioPorId(id)
        );
        return ResponseEntity.status(200).body(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> atualizarPorId (
            @PathVariable Integer id,
            @RequestBody @Valid FuncionarioRequestDto funcionarioAtualizar
    ) {
        Funcionario funcionarioParaAtualizar = FuncionarioMapper.toEntity(funcionarioAtualizar);
        FuncionarioResponseDto funcionarioAtualizado = FuncionarioMapper.toResponseDto(
                funcionarioService.atualizarFuncionarioPorId(id, funcionarioParaAtualizar)
        );
        return ResponseEntity.status(200).body(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        funcionarioService.removerFuncionarioPorId(id);
        return ResponseEntity.status(204).build();
    }
}
