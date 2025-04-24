package school.sptech.CRUDBackend.controller.controllerLogin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.dtoLogin.*;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.service.serviceLogin.FuncionarioServiceLogin;

import java.util.List;

@RestController
@RequestMapping("/funcionarios-login")
public class FuncionarioControllerLogin {
    @Autowired
    private FuncionarioServiceLogin funcionarioServiceLogin;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid FuncionarioCriacaoDto funcionarioCriacaoDto) {

        final Funcionario novoFuncionario = FuncionarioMapper.of(funcionarioCriacaoDto);
        this.funcionarioServiceLogin.criar(novoFuncionario);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<FuncionarioTokenDto> login(@RequestBody FuncionarioLoginDto funcionarioLoginDto) {

        final Funcionario usuario = FuncionarioMapper.of(funcionarioLoginDto);
        FuncionarioTokenDto funcionarioTokenDto = this.funcionarioServiceLogin.autenticar(usuario);

        return ResponseEntity.status(200).body(funcionarioTokenDto);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<FuncionarioListarDto>> listarTodos() {

        List<FuncionarioListarDto> usuariosEncontrados = this.funcionarioServiceLogin.listarTodos();

        if (usuariosEncontrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuariosEncontrados);

    }
}
