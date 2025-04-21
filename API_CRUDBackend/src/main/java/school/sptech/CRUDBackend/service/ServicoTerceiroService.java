package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.exception.servicoTerceiro.ServicoTerceiroConflitoException;
import school.sptech.CRUDBackend.exception.servicoTerceiro.ServicoTerceiroNaoEncontradoException;
import school.sptech.CRUDBackend.repository.ServicoTerceiroRepository;
import school.sptech.CRUDBackend.entity.ServicoTerceiro;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoTerceiroService {
    private final ServicoTerceiroRepository servicoTerceiroRepository;

    public ServicoTerceiro cadastrarServicoTerceiro(ServicoTerceiro servicoTerceiro) {
        if (servicoTerceiroRepository.existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(
                servicoTerceiro.getEmail(), servicoTerceiro.getIdentificacao(), servicoTerceiro.getEndereco())
        ) {
            throw new ServicoTerceiroConflitoException("Já existe um serviço de terceiro cadastrado.");
        }

        return servicoTerceiroRepository.save(servicoTerceiro);
    }

    public List<ServicoTerceiro> verificarTodosServicosTerceiro() {
        return servicoTerceiroRepository.findAll();
    }

    public ServicoTerceiro buscarServicoTerceiroPorId(Integer id) {
        return servicoTerceiroRepository.findById(id)
                .orElseThrow(() -> new ServicoTerceiroNaoEncontradoException("O serviço não foi encotrado"));
    }

    public ServicoTerceiro atualizarServicoTerceiroPorId (Integer id, ServicoTerceiro servicoTerceiroAtualizar) {
        if(servicoTerceiroRepository.existsById(id)) {
            servicoTerceiroAtualizar.setIdServicoTerceiro(id);
            return servicoTerceiroRepository.save(servicoTerceiroAtualizar);
        }
        throw new ServicoTerceiroNaoEncontradoException("O serviço não foi encotrado");
    }

    public void removerServicoTerceiroPorId(Integer id) {
        if (servicoTerceiroRepository.existsById(id)) {
            servicoTerceiroRepository.deleteById(id);
        } else {
            throw new ServicoTerceiroNaoEncontradoException("O serviço não foi encotrado");
        }
    }
}
