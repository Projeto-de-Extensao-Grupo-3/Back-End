package school.sptech.CleanArchitecture.core.application.exception.Lote;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LoteConflitoException extends RuntimeException {
    public LoteConflitoException(String message) {
        super(message);
    }
}
