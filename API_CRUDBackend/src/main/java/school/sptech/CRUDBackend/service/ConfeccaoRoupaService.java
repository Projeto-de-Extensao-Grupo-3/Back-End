package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.ConfeccaoRoupa;
import school.sptech.CRUDBackend.exception.confeccaoRoupa.ConfeccaoRoupaConflitoException;
import school.sptech.CRUDBackend.exception.confeccaoRoupa.ConfeccaoRoupaNaoEncontradaException;
import school.sptech.CRUDBackend.repository.ConfeccaoRoupaRepository;

@Service
@RequiredArgsConstructor
public class ConfeccaoRoupaService {
    private final ConfeccaoRoupaRepository confeccaoRoupaRepository;

    public ConfeccaoRoupa cadastrarconfeccaoRoupa(ConfeccaoRoupa confeccaoRoupa) {
        if (confeccaoRoupaRepository.existsByRoupaAndTecido(
                confeccaoRoupa.getRoupa(), confeccaoRoupa.getTecido()
        )) {
            throw new ConfeccaoRoupaConflitoException("A roupa já está relacionada com este tecido");
        }
        return confeccaoRoupaRepository.save(confeccaoRoupa);
    }

    public void deletarConfeccaoRoupa(Integer id) {
        if (confeccaoRoupaRepository.existsById(id)) {
            confeccaoRoupaRepository.deleteById(id);
        } else {
            throw new ConfeccaoRoupaNaoEncontradaException("relação entre roupa e tecido não encontrada");
        }
    }

    public ConfeccaoRoupa atualizarConfeccaoRoupa(Integer id, ConfeccaoRoupa confeccaoRoupa) {
        if (confeccaoRoupaRepository.existsById(id)) {
            confeccaoRoupa.setIdConfeccaoRoupa(id);
            return confeccaoRoupaRepository.save(confeccaoRoupa);
        }
        throw new ConfeccaoRoupaNaoEncontradaException("Esse registro de confecção de roupa não existe");
    }
}
