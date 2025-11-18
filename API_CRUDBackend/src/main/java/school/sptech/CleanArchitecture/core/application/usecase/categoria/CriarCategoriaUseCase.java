package school.sptech.CleanArchitecture.core.application.usecase.categoria;

import school.sptech.CRUDBackend.exception.Categoria.CategoriaConflitoException;
import school.sptech.CleanArchitecture.core.adapters.AlertaGateway;
import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.application.command.alerta.CriarAlertaCommand;
import school.sptech.CleanArchitecture.core.application.command.categoria.CategoriaPaiCommand;
import school.sptech.CleanArchitecture.core.application.command.categoria.CriarCategoriaCommand;
import school.sptech.CleanArchitecture.core.application.mapper.CategoriaMapper;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;

public class CriarCategoriaUseCase {

    private final CategoriaGateway gateway;

    public CriarCategoriaUseCase(CategoriaGateway gateway) {
        this.gateway = gateway;
    }

    public Categoria executar(CriarCategoriaCommand command) {
        if(gateway.existsByNome(command.nome())){
            throw new CategoriaConflitoException("Já existe uma categoria cadastrada com o nome de: "+ command.nome());
        }
        var categoriaParaRegistrar = CategoriaMapper.ofCriarCategoriaCommand(command);

        return gateway.save(categoriaParaRegistrar);
    }
}
