package school.sptech.CRUDBackend.service;

import org.hibernate.mapping.Array;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.CRUDBackend.entity.ConfeccaoRoupa;
import school.sptech.CRUDBackend.entity.Prateleira;
import school.sptech.CRUDBackend.exception.Prateleira.PrateleiraConflitoException;
import school.sptech.CRUDBackend.exception.Prateleira.PrateleiraNaoEncontradaException;
import school.sptech.CRUDBackend.repository.PrateleiraRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrateleiraServiceTest {

    @Mock
    private PrateleiraRepository repository;

    @Mock
    private Prateleira entity;

    @InjectMocks
    private PrateleiraService service;

    @Test
    void deveCadastrarPrateleiraComSucessoQuandoDadosValidos() {
        entity.setIdPrateleira(1);
        entity.setCodigo("2345678");

        when(repository.save(entity)).thenReturn(entity);

        Prateleira resultado = service.cadastrarPrateleira(entity);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdPrateleira());
        assertEquals("2345678",resultado.getCodigo());
    }

    @Test
    void deveFalharCadastroPrateleiraQuandoPrateleiraJaExiste() {
        entity.setIdPrateleira(1);

        when(repository.existsById(1)).thenReturn(true);

        assertThrows(PrateleiraConflitoException.class, () -> {
            service.cadastrarPrateleira(entity);
        });
    }

    @Test
    void deveRetornarListaDePrateleirasQuandoHouverRegistros() {
        Prateleira prateleira1 = new Prateleira(1, "2345678");
        Prateleira prateleira2 = new Prateleira(2, "2345678");

        List<Prateleira> prateleiras = new ArrayList<>();
        prateleiras.add(prateleira1);
        prateleiras.add(prateleira2);

        when(repository.findAll()).thenReturn(prateleiras);

        List<Prateleira> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        assertEquals("2345678", resultado.get(0).getCodigo());
        assertEquals("2345678", resultado.get(1).getCodigo());
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
        Prateleira prateleira = new Prateleira(1, "2345678");

        when(repository.findById(prateleira.getIdPrateleira())).thenReturn(Optional.of(prateleira));

        Prateleira resultado = service.buscarPorId(prateleira.getIdPrateleira());

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
        Prateleira prateleira = new Prateleira(1, "2345678");

        when(repository.findByCodigo(prateleira.getCodigo())).thenReturn(Optional.of(prateleira));

        Prateleira resultado = service.buscarPorCodigo(prateleira.getCodigo());

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
        Prateleira prateleira = new Prateleira(1, "2345678");

        when(repository.existsById(prateleira.getIdPrateleira())).thenReturn(true);
        prateleira.setIdPrateleira(1);
        prateleira.setCodigo("8765432");
        Prateleira resultado = service.atualizarPorId(prateleira.getIdPrateleira(), prateleira);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdPrateleira());
        assertEquals("8765432", resultado.getCodigo());
    }

    @Test
    void deveFalharAoAtualizarPrateleiraComIdInvalido() {
        Prateleira prateleira = new Prateleira(1, "2345678");

        when(repository.existsById(prateleira.getIdPrateleira())).thenReturn(false);

        assertThrows(PrateleiraNaoEncontradaException.class,
                () -> service.atualizarPorId(prateleira.getIdPrateleira(), prateleira));
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