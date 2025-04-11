package school.sptech.CRUDBackend.exception.permissao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PermissaoConflitoException extends RuntimeException {
    public PermissaoConflitoException(String message) {
        super(message);
    }
}
