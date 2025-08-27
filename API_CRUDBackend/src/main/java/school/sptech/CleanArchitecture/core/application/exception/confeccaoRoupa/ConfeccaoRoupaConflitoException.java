package school.sptech.CleanArchitecture.core.application.exception.confeccaoRoupa;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConfeccaoRoupaConflitoException extends RuntimeException {
    public ConfeccaoRoupaConflitoException(String message) {
        super(message);
    }
}
