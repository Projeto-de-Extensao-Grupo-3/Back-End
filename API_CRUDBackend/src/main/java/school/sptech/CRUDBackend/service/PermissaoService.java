package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.Permissao;
import school.sptech.CRUDBackend.repository.PermissaoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissaoService {
    private final PermissaoRepository permissaoRepository;

    public List<Permissao> verificarTodasPermissoes() {
        return permissaoRepository.findAll();
    }
}
