package school.sptech.CleanArchitecture.core.domain.observer;

import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public interface ObserverLoteItem {
    void atualizarPreco(ItemEstoque itemEstoque);
}
