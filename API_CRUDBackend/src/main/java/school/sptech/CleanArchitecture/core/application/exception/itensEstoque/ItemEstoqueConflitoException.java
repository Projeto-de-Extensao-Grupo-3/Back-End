package school.sptech.CleanArchitecture.core.application.exception.itensEstoque;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ItemEstoqueConflitoException extends RuntimeException {
    public ItemEstoqueConflitoException(String message) {
        super(message);
    }
}
