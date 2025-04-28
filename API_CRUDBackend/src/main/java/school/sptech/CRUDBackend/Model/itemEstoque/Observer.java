package school.sptech.CRUDBackend.Model.itemEstoque;

import school.sptech.CRUDBackend.entity.ItemEstoque;

public interface Observer {
    void atualizarQuantidade(ItemEstoque itemEstoque);
}
