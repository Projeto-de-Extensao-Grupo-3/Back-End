
package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.CRUDBackend.entity.ConfeccaoRoupa;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.exception.confeccaoRoupa.ConfeccaoRoupaConflitoException;
import school.sptech.CRUDBackend.exception.confeccaoRoupa.ConfeccaoRoupaNaoEncontradaException;
import school.sptech.CRUDBackend.repository.ConfeccaoRoupaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfeccaoRoupaServiceTest {

    @Mock
    private ConfeccaoRoupaRepository repository;

    @InjectMocks
    private ConfeccaoRoupaService service;

    @Test
    void deveCadastrarConfeccaoRoupaComSucessoQuandoDadosValidos() {
        ItemEstoque tecidoEstoque = new ItemEstoque();
        tecidoEstoque.setIdItemEstoque(1);

        ItemEstoque roupaEstoque = new ItemEstoque();
        roupaEstoque.setIdItemEstoque(2);

        ConfeccaoRoupa confeccaoRoupa = new ConfeccaoRoupa(1, roupaEstoque, tecidoEstoque, 1.0);

        when(repository.existsByRoupaAndTecido(roupaEstoque, tecidoEstoque)).thenReturn(false);
        when(repository.save(confeccaoRoupa)).thenReturn(confeccaoRoupa);

        ConfeccaoRoupa resultado = service.cadastrarconfeccaoRoupa(confeccaoRoupa);

        assertNotNull(resultado);
        assertEquals(1.0, resultado.getQtdTecido());
        assertEquals(tecidoEstoque, resultado.getTecido());
        assertEquals(roupaEstoque, resultado.getRoupa());
    }

    @Test
    void deveFalharAoCadastrarConfeccaoRoupaComRoupaETecidoJaRelacionados() {
        ItemEstoque tecidoEstoque = new ItemEstoque();
        tecidoEstoque.setIdItemEstoque(1);

        ItemEstoque roupaEstoque = new ItemEstoque();
        roupaEstoque.setIdItemEstoque(2);

        ConfeccaoRoupa confeccaoRoupa = new ConfeccaoRoupa(1, roupaEstoque, tecidoEstoque, 1.0);

        when(repository.existsByRoupaAndTecido(roupaEstoque, tecidoEstoque)).thenReturn(true);

        ConfeccaoRoupaConflitoException excecao = assertThrows(ConfeccaoRoupaConflitoException.class, () -> {
            service.cadastrarconfeccaoRoupa(confeccaoRoupa);
        });

        assertEquals("A roupa já está relacionada com este tecido", excecao.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveDeletarConfeccaoRoupaPorIdQuandoIdValido() {
        int id = 1;

        when(repository.existsById(id)).thenReturn(true);

        service.deletarConfeccaoRoupa(id);

        verify(repository).deleteById(id);
    }

    @Test
    void deveFalharDeletarConfeccaoRoupaPorIdQuandoIdInvalido() {
        Integer id = 999;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(ConfeccaoRoupaNaoEncontradaException.class, () -> service.deletarConfeccaoRoupa(id));
    }

    @Test
    void deveAtualizarConfeccaoRoupaQuandoDadosValidos() {
        ItemEstoque tecidoEstoque = new ItemEstoque();
        tecidoEstoque.setIdItemEstoque(1);

        ItemEstoque roupaEstoque = new ItemEstoque();
        roupaEstoque.setIdItemEstoque(2);

        ConfeccaoRoupa confeccaoRoupa = new ConfeccaoRoupa(1, roupaEstoque, tecidoEstoque, 1.0);

        when(repository.existsById(1)).thenReturn(true);
        when(repository.save(confeccaoRoupa)).thenReturn(confeccaoRoupa);

        ConfeccaoRoupa resultado = service.atualizarConfeccaoRoupa(1, confeccaoRoupa);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdConfeccaoRoupa());
        assertEquals(roupaEstoque, resultado.getRoupa());
        assertEquals(tecidoEstoque, resultado.getTecido());
        assertEquals(1.0, resultado.getQtdTecido());
    }

    @Test
    void deveFalharAtualizarConfeccaoRoupaQuandoIdInvalido() {
        ItemEstoque tecidoEstoque = new ItemEstoque();
        tecidoEstoque.setIdItemEstoque(1);

        ItemEstoque roupaEstoque = new ItemEstoque();
        roupaEstoque.setIdItemEstoque(2);

        ConfeccaoRoupa confeccaoRoupa = new ConfeccaoRoupa(999, roupaEstoque, tecidoEstoque, 1.0);

        when(repository.existsById(999)).thenReturn(false);

        assertThrows(ConfeccaoRoupaNaoEncontradaException.class, () -> {
            service.atualizarConfeccaoRoupa(999, confeccaoRoupa);
        });
    }
}
