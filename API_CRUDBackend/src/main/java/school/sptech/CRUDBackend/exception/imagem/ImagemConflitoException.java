package school.sptech.CRUDBackend.exception.imagem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ImagemConflitoException extends RuntimeException {
    public ImagemConflitoException(String message) {
        super(message);
    }
}
