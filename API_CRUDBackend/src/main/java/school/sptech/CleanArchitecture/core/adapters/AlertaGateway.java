package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public interface AlertaGateway {

    Alerta save(Alerta alerta);

    List<Alerta> findByItemEstoque(ItemEstoque itemEstoque);
}
