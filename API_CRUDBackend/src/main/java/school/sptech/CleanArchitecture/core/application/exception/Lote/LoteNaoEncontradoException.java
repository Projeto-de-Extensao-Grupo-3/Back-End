package school.sptech.CleanArchitecture.core.application.exception.Lote;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoteNaoEncontradoException extends RuntimeException {
    public LoteNaoEncontradoException(String message) {
        super(message);
    }
}
