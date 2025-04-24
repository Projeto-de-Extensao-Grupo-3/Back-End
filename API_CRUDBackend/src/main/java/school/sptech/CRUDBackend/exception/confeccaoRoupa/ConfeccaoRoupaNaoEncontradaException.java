package school.sptech.CRUDBackend.exception.confeccaoRoupa;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConfeccaoRoupaNaoEncontradaException extends RuntimeException {
  public ConfeccaoRoupaNaoEncontradaException(String message) {
    super(message);
  }
}
