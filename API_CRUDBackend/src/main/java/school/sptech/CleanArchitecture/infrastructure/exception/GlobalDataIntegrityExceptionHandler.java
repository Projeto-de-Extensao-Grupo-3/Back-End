package school.sptech.CleanArchitecture.infrastructure.exception;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDataIntegrityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        String errorMessage = "Impossível deletar: entidade referenciada por outras";
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }
}