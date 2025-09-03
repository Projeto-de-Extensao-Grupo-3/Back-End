package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.alerta;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.AlertaGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertaAdapter implements AlertaGateway {

    private final AlertaRepository repository;

    public AlertaAdapter(AlertaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Alerta save(Alerta alerta) {
        var alertaEntity = AlertaEntityMapper.ofDomain(alerta);
        var alertaSalvo = repository.save(alertaEntity);
        return AlertaEntityMapper.ofEntity(alertaSalvo);
    }

    @Override
    public List<Alerta> findByItemEstoque(ItemEstoque itemEstoque) {
        ItemEstoqueEntity itemEstoqueEntity = new ItemEstoqueEntity();
        itemEstoqueEntity.setIdItemEstoque(itemEstoque.getIdItemEstoque());
        List<AlertaEntity> entities = repository.findByItemEstoque(itemEstoqueEntity);
        return entities.stream().map(AlertaEntityMapper::ofEntity).collect(Collectors.toList());
    }
}
