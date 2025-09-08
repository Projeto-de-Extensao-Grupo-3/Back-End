package school.sptech.CleanArchitecture.infrastructure.web.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(FuncionarioConflitoException.class)
    public ResponseEntity<Map<String, String>> duplicidade(FuncionarioConflitoException ex) {
        Map<String, String> error = Map.of(
                "erro:", "Erro de conflito de dados: %s".formatted(ex.getMessage())
        );

        return ResponseEntity.status(409).body(error);
    }
}
