
package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.CRUDBackend.entity.Prateleira;
import school.sptech.CRUDBackend.exception.Prateleira.PrateleiraConflitoException;
import school.sptech.CRUDBackend.exception.Prateleira.PrateleiraNaoEncontradaException;
import school.sptech.CRUDBackend.repository.PrateleiraRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrateleiraServiceTest {

    @Mock
    private PrateleiraRepository repository;

    @InjectMocks
    private PrateleiraService service;

    private Prateleira entity;

    @BeforeEach
    void setUp() {
        entity = new Prateleira(1, "2345678");
    }

    @Test
    void deveCadastrarPrateleiraComSucessoQuandoDadosValidos() {
        when(repository.existsByCodigo(entity.getCodigo())).thenReturn(false);
        when(repository.save(entity)).thenReturn(entity);

        Prateleira resultado = service.cadastrarPrateleira(entity);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdPrateleira());
        assertEquals("2345678", resultado.getCodigo());
    }

    @Test
    void deveFalharCadastroPrateleiraQuandoPrateleiraJaExiste() {
        when(repository.existsByCodigo(entity.getCodigo())).thenReturn(true);

        assertThrows(PrateleiraConflitoException.class, () -> {
            service.cadastrarPrateleira(entity);
        });
    }

    @Test
    void deveRetornarListaDePrateleirasQuandoHouverRegistros() {
        Prateleira prateleira1 = new Prateleira(1, "2345678");
        Prateleira prateleira2 = new Prateleira(2, "8765432");

        List<Prateleira> prateleiras = new ArrayList<>();
        prateleiras.add(prateleira1);
        prateleiras.add(prateleira2);

        when(repository.findAll()).thenReturn(prateleiras);

        List<Prateleira> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        assertEquals("2345678", resultado.get(0).getCodigo());
        assertEquals("8765432", resultado.get(1).getCodigo());
    }

    @Test
    void deveFalharAoRetornarListaDePrateleirasVazia() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Prateleira> resultado = service.listarTodos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRetornarPrateleiraPorIdValido() {
        when(repository.findById(entity.getIdPrateleira())).thenReturn(Optional.of(entity));

        Prateleira resultado = service.buscarPorId(entity.getIdPrateleira());

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdPrateleira());
        assertEquals("2345678", resultado.getCodigo());
    }

    @Test
    void deveFalharRetornoDePrateleiraPorIdInvalido() {
        int idInvalido = 999;

        when(repository.findById(idInvalido)).thenReturn(Optional.empty());

        assertThrows(PrateleiraNaoEncontradaException.class,
                () -> service.buscarPorId(idInvalido));
    }

    @Test
    void deveRetornarPrateleiraPorCodigoValido() {
        when(repository.findByCodigo(entity.getCodigo())).thenReturn(Optional.of(entity));

        Prateleira resultado = service.buscarPorCodigo(entity.getCodigo());

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdPrateleira());
        assertEquals("2345678", resultado.getCodigo());
    }

    @Test
    void deveFalharRetornoDePrateleiraPorCodigoInvalido() {
        String codigoInvalido = "";

        when(repository.findByCodigo(codigoInvalido)).thenReturn(Optional.empty());

        assertThrows(PrateleiraNaoEncontradaException.class,
                () -> service.buscarPorCodigo(codigoInvalido));
    }

    @Test
    void deveAtualizarPrateleiraPorIdValido() {
        when(repository.existsById(entity.getIdPrateleira())).thenReturn(true);
        when(repository.save(entity)).thenReturn(entity);

        entity.setCodigo("8765432");
        Prateleira resultado = service.atualizarPorId(entity.getIdPrateleira(), entity);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdPrateleira());
        assertEquals("8765432", resultado.getCodigo());
    }

    @Test
    void deveFalharAoAtualizarPrateleiraComIdInvalido() {
        when(repository.existsById(entity.getIdPrateleira())).thenReturn(false);

        assertThrows(PrateleiraNaoEncontradaException.class,
                () -> service.atualizarPorId(entity.getIdPrateleira(), entity));
    }

    @Test
    void deveRemoverPorIdQuandoIdValido() {
        int id = 1;

        when(repository.existsById(id)).thenReturn(true);

        service.removerPorId(id);
        verify(repository).deleteById(id);
    }

    @Test
    void deveFalharAoRemoverPorIdQuandoIdInvalido() {
        int id = 999;

        when(repository.existsById(id)).thenReturn(false);

        assertThrows(PrateleiraNaoEncontradaException.class,
                () -> service.removerPorId(id));
    }
}
