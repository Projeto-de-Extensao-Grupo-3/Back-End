package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.Permissao;
import school.sptech.CRUDBackend.exception.permissao.PermissaoConflitoException;
import school.sptech.CRUDBackend.exception.permissao.PermissaoNaoEncontradaException;
import school.sptech.CRUDBackend.repository.PermissaoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissaoService {
    private final PermissaoRepository permissaoRepository;

//    public Permissao cadastrarPermissao(Permissao permissaoParaCadastrar) {
//        if (permissaoRepository.existsByDescricao(permissaoParaCadastrar.getDescricao())) {
//            throw new PermissaoConflitoException("A permissão já existe");
//        }
//        return permissaoRepository.save(permissaoParaCadastrar);
//    }

    public List<Permissao> verificarTodasPermissoes() {
        return permissaoRepository.findAll();
    }

//    public Permissao atualizarPermissao(Integer id, Permissao permissaoParaAtualizar) {
//        if (permissaoRepository.existsById(id)) {
//            permissaoParaAtualizar.setId(id);
//            return permissaoRepository.save(permissaoParaAtualizar);
//        }
//        throw new PermissaoNaoEncontradaException("A permissão não existe");
//    }

//    public void deletar(Integer id) {
//        if (permissaoRepository.existsById(id)) {
//            permissaoRepository.deleteById(id);
//        } else {
//            throw new PermissaoNaoEncontradaException("A permissão não existe");
//        }
//    }
}
