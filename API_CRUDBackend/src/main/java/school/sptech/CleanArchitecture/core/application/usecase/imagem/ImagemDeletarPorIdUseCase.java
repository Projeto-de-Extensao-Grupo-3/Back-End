package school.sptech.CleanArchitecture.core.application.usecase.imagem;

import school.sptech.CRUDBackend.exception.imagem.ImagemNaoEncontradaException;
import school.sptech.CleanArchitecture.core.adapters.ImagemGateway;

public class ImagemDeletarPorIdUseCase {

    private final ImagemGateway gateway;

    public ImagemDeletarPorIdUseCase(ImagemGateway gateway) {
        this.gateway = gateway;
    }

    public Void execute(Integer id) {
        if (gateway.existsById(id)) {
            gateway.deleteById(id);
        }
        throw new ImagemNaoEncontradaException("A imagem não foi encontrada");
    }
}
