package school.sptech.CRUDBackend.exception.servicoTerceiro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ServicoTerceiroConflitoException extends RuntimeException {
    public ServicoTerceiroConflitoException(String message) {
        super(message);
    }
}
