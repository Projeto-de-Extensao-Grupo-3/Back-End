package school.sptech.CleanArchitecture.core.application.exception.itensEstoque;

public class ItemEstoqueNaoEncontradoException extends RuntimeException {
    public ItemEstoqueNaoEncontradoException(String message) {
        super(message);
    }
}
