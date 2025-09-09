
package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FuncionarioAdapter implements FuncionarioGateway {

    private final FuncionarioRepository repository;

    @Override
    public Funcionario save(Funcionario funcionario) {
        var entity = FuncionarioEntityMapper.ofDomain(funcionario);
        var saved = repository.save(entity);
        return FuncionarioEntityMapper.ofEntity(saved);
    }

    @Override
    public Optional<Funcionario> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(FuncionarioEntityMapper::ofEntity);
    }

    @Override
    public Optional<Funcionario> findById(Integer id) {
        return repository.findById(id).map(FuncionarioEntityMapper::ofEntity);
    }

    @Override
    public List<Funcionario> findAll() {
        return repository.findAll().stream()
                .map(FuncionarioEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByCpfOrEmail(String cpf, String email) {
        return repository.existsByCpfOrEmail(cpf, email);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
