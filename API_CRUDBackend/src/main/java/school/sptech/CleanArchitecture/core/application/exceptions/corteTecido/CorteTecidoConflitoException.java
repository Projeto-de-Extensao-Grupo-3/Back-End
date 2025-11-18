package school.sptech.CleanArchitecture.core.application.exceptions.corteTecido;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CorteTecidoConflitoException extends RuntimeException {
    public CorteTecidoConflitoException(String message) {
        super(message);
    }
}
