package school.sptech.CleanArchitecture.core.application.exceptions.itensEstoque;

public class ItemEstoqueNaoEncontradoException extends RuntimeException {
    public ItemEstoqueNaoEncontradoException(String message) {
        super(message);
    }
}
