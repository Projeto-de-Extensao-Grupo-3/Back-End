
package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioAdapter implements FuncionarioGateway {

    private final FuncionarioRepository repository;

    public FuncionarioAdapter(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        FuncionarioEntity entity = FuncionarioEntityMapper.ofDomain(funcionario);
        FuncionarioEntity saved = repository.save(entity);
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

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public List<Funcionario> findByNomeContainsIgnoreCase(String nome) {
        return repository.findByNomeContainsIgnoreCase(nome).stream()
                .map(FuncionarioEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }
}
