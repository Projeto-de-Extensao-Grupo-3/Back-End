package school.sptech.CRUDBackendV1.exception.servicoTerceiro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServicoTerceiroNaoEncontradoException extends RuntimeException {
    public ServicoTerceiroNaoEncontradoException(String message) {
        super(message);
    }
}
