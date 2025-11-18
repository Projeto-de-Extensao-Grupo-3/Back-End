package school.sptech.CleanArchitecture.core.application.exceptions.itensEstoque;

public class ItemEstoqueConflitoException extends RuntimeException {
  public ItemEstoqueConflitoException(String message) {
    super(message);
  }
}
