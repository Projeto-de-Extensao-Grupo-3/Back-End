package school.sptech.CRUDBackendV1.service;

import org.springframework.stereotype.Service;
import school.sptech.CRUDBackendV1.exception.servicoTerceiro.ServicoTerceiroConflitoException;
import school.sptech.CRUDBackendV1.exception.servicoTerceiro.ServicoTerceiroNaoEncontradoException;
import school.sptech.CRUDBackendV1.repository.ServicoTerceiroRepository;
import school.sptech.CRUDBackendV1.entity.ServicoTerceiro;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoTerceiroService {

    private ServicoTerceiroRepository repository;

    public void setRepository(ServicoTerceiroRepository repository) {
        this.repository = repository;
    }

    public ServicoTerceiro cadastrarServicoTerceiro(ServicoTerceiro servicoTerceiro) {
        if (repository.existsByEmailOrIdentificacaoOrEnderecoIgnoreCaseAll(
                servicoTerceiro.getEmail(), servicoTerceiro.getIdentificacao(), servicoTerceiro.getEndereco())
        ) {
            throw new ServicoTerceiroConflitoException("Já existe um serviço de terceiro cadastrado.");
        }

        return repository.save(servicoTerceiro);
    }

    public List<ServicoTerceiro> todosServicoTerceiroes () {
        return repository.findAll();
    }

    public ServicoTerceiro servicoTerceiroPorId (Integer id) {
        Optional<ServicoTerceiro> servicoTerceiroBuscado = repository.findById(id);

        if(servicoTerceiroBuscado.isPresent()) {
            return servicoTerceiroBuscado.get();
        }
        throw new ServicoTerceiroNaoEncontradoException("O serviço não foi encotrado");
    }

    public ServicoTerceiro atualizarServicoTerceiroPorId (Integer id, ServicoTerceiro servicoTerceiroAtualizar) {
        if(repository.existsById(id)) {
            servicoTerceiroAtualizar.setIdServicoTerceiro(id);
            ServicoTerceiro servicoTerceiroAlterado = repository.save(servicoTerceiroAtualizar);
            return servicoTerceiroAlterado;
        }
        throw new ServicoTerceiroNaoEncontradoException("O serviço não foi encotrado");
    }

    public void removerServicoTerceiroPorId(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new ServicoTerceiroNaoEncontradoException("O serviço não foi encotrado");
    }
}
