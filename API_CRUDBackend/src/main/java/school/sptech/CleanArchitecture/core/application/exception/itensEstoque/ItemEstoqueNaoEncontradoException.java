package school.sptech.CleanArchitecture.core.application.exception.itensEstoque;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemEstoqueNaoEncontradoException extends RuntimeException {
    public ItemEstoqueNaoEncontradoException(String message) {
        super(message);
    }
}
