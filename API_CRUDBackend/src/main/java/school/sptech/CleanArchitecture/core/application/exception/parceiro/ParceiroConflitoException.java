package school.sptech.CleanArchitecture.core.application.exception.parceiro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ParceiroConflitoException extends RuntimeException {
    public ParceiroConflitoException(String message) {
        super(message);
    }
}
