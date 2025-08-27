package school.sptech.CleanArchitecture.core.application.exception.parceiro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParceiroNaoEncontradoException extends RuntimeException {
    public ParceiroNaoEncontradoException(String message) {
        super(message);
    }
}
