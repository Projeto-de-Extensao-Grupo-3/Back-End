package school.sptech.CRUDBackend.exception.imagem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImagemNaoEncontradaException extends RuntimeException {
    public ImagemNaoEncontradaException(String message) {
        super(message);
    }
}
