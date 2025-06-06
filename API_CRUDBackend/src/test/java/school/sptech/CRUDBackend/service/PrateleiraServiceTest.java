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
import school.sptech.CRUDBackend.repository.PrateleiraRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
}