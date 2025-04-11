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

    public CorteTecido cadastrarCorteTecido(CorteTecido corteTecido) {
        return corteTecidoRepository.save(corteTecido);
    }

    public List<CorteTecido> verificarTodosCortesTecido() {
        return corteTecidoRepository.findAll();
    }

    public CorteTecido buscarCorteTecidoPorId(Integer id) {
        return corteTecidoRepository.findById(id)
                .orElseThrow(() -> new CorteTecidoNaoEncontradoException("O registro de corte de tecido não existe"));
    }

    public CorteTecido atualizarCorteTecido(Integer id, CorteTecido corteTecido) {
        if (corteTecidoRepository.existsById(id)) {
            corteTecido.setId(id);
            return corteTecidoRepository.save(corteTecido);
        }
        throw new CorteTecidoNaoEncontradoException("O registro de corte de tecido não existe");
    }

    public void deletarCorteTecido(Integer id) {
        if (corteTecidoRepository.existsById(id)) {
            corteTecidoRepository.deleteById(id);
        } else {
            throw new CorteTecidoNaoEncontradoException("O registro de corte de tecido não existe");
        }
    }
}
