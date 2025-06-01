package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.Alerta;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.repository.AlertaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {
    private final AlertaRepository repository;

    public Alerta criarAlerta(Alerta alerta) {
        return repository.save(alerta);
    }

    public List<Alerta> listarAlertasDoItem(ItemEstoque itemEstoque) {
        return repository.findByItemEstoque(itemEstoque);
    }
}
