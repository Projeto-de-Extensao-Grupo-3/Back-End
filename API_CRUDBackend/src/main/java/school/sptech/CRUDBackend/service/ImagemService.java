package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.Imagem;
import school.sptech.CRUDBackend.exception.imagem.ImagemConflitoException;
import school.sptech.CRUDBackend.exception.imagem.ImagemNaoEncontradaException;
import school.sptech.CRUDBackend.repository.ImagemRepository;

@Service
@RequiredArgsConstructor
public class ImagemService {
    private final ImagemRepository repository;

    public Imagem cadastrarImagem(Imagem imagem) {
        if (repository.existsByUrl(imagem.getUrl())) {
            throw new ImagemConflitoException("Essa imagem já foi cadastrada");
        }
        return repository.save(imagem);
    }

    public Imagem atualizarImagem(Integer id, Imagem imagem) {
        if (repository.existsById(id)) {
            imagem.setIdImagem(id);
            return repository.save(imagem);
        }
        throw new ImagemNaoEncontradaException("A imagem não foi encontrada");
    }

    public void deletarImagem(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ImagemNaoEncontradaException("A imagem não foi encontrada");
        }
    }
}
