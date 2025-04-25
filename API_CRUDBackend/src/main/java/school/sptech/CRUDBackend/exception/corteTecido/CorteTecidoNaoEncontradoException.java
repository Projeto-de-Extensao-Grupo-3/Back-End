package school.sptech.CRUDBackend.exception.corteTecido;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CorteTecidoNaoEncontradoException extends RuntimeException {
    public CorteTecidoNaoEncontradoException(String message) {
        super(message);
    }
}
