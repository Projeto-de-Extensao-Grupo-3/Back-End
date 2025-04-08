package school.sptech.CRUDBackend.exception.itensEstoque;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ItemEstoqueConflitoException extends RuntimeException {
    public ItemEstoqueConflitoException(String message) {
        super(message);
    }
}
