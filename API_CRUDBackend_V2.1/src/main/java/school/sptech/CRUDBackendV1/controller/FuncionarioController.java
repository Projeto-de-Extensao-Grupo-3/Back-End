package school.sptech.CRUDBackendV1.controller;

import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackendV1.entity.Funcionario;
import school.sptech.CRUDBackendV1.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@Setter
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(
            @RequestBody Funcionario funcionarioCad
    ) {
        Funcionario funcionarioCadastrar = funcionarioService.cadastrarFuncionario(funcionarioCad);
        return ResponseEntity.status(201).body(funcionarioCadastrar);
    }

    @GetMapping("/todosFuncionarios")
    public ResponseEntity<List<Funcionario>> verificarTodos() {
        List<Funcionario> trouxeTodosFuncionarios = funcionarioService.verificarTodosFuncionarios();

        if(trouxeTodosFuncionarios == null) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(trouxeTodosFuncionarios);
    }

//    @GetMapping
//    public ResponseEntity<Void> login(
//            @RequestBody Funcionario funcionarioLogar
//    ) {
//        boolean funcionarioAutenticado = funcionarioService.loginFuncionario(funcionarioLogar);
//
//        if (funcionarioAutenticado) {
//            return ResponseEntity.status(200).build();
//        }
//        return ResponseEntity.status(401).build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarPorId (
            @PathVariable Integer id,
            @RequestBody Funcionario funcionarioAtualizar
    ) {
        Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionarioPorId(id, funcionarioAtualizar);
        return ResponseEntity.status(200).body(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(
            @PathVariable Integer id
    ) {
        funcionarioService.removerFuncionarioPorId(id);
        return ResponseEntity.status(200).build();
    }
}
