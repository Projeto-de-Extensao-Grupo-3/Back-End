package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.exception.parceiro.ParceiroConflitoException;
import school.sptech.CRUDBackend.exception.parceiro.ParceiroNaoEncontradoException;
import school.sptech.CRUDBackend.entity.Parceiro;
import school.sptech.CRUDBackend.repository.ParceiroRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParceiroService {
    private final ParceiroRepository parceiroRepository;

    public Parceiro cadastrarParceiro(Parceiro parceiro) {
        if (parceiroRepository.existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(
                parceiro.getEmail(), parceiro.getIdentificacao(), parceiro.getEndereco())
        ) {
            throw new ParceiroConflitoException("Esse provedor de serviço já foi cadastrado.");
        }

        return parceiroRepository.save(parceiro);
    }

    public List<Parceiro> verificarTodosParceiros(String categoria) {
        return parceiroRepository.findByCategoria(categoria);
    }

    public List<Parceiro> buscarParceiroPorNome(String categoria, String nome) {
        return parceiroRepository.findByCategoriaAndNomeContainsIgnoreCase(categoria, nome);
    }

    public Parceiro atualizarParceiroPorId(Integer id, Parceiro parceiroAtualizar) {
        if(parceiroRepository.existsById(id)) {
            parceiroAtualizar.setIdParceiro(id);
            return parceiroRepository.save(parceiroAtualizar);
        }
        throw new ParceiroNaoEncontradoException("O parceiro não foi encotrado");
    }

    public void removerParceiroPorId(Integer id) {
        if (parceiroRepository.existsById(id)) {
            parceiroRepository.deleteById(id);
        } else {
            throw new ParceiroNaoEncontradoException("O parceiro não foi encotrado");
        }
    }
}
