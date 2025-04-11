package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.CorteTecido;
import school.sptech.CRUDBackend.exception.corteTecido.CorteTecidoNaoEncontradoException;
import school.sptech.CRUDBackend.repository.CorteTecidoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CorteTecidoService {
    private final CorteTecidoRepository corteTecidoRepository;

    public CorteTecido cadastrar(CorteTecido corteTecido) {
        return corteTecidoRepository.save(corteTecido);
    }

    public CorteTecido buscarPorId(Integer id) {
        return corteTecidoRepository.findById(id)
                .orElseThrow(() -> new CorteTecidoNaoEncontradoException("O registro de corte de tecido não existe"));
    }

    public List<CorteTecido> listar() {
        return corteTecidoRepository.findAll();
    }

    public CorteTecido atualizar(Integer id, CorteTecido corteTecido) {
        if (corteTecidoRepository.existsById(id)) {
            corteTecido.setId(id);
            return corteTecidoRepository.save(corteTecido);
        }
        throw new CorteTecidoNaoEncontradoException("O registro de corte de tecido não existe");
    }

    public void deletar(Integer id) {
        if (corteTecidoRepository.existsById(id)) {
            corteTecidoRepository.deleteById(id);
        } else {
            throw new CorteTecidoNaoEncontradoException("O registro de corte de tecido não existe");
        }
    }
}
