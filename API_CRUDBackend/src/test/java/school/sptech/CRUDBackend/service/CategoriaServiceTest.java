
package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.CRUDBackend.entity.Categoria;
import school.sptech.CRUDBackend.exception.Categoria.CategoriaConflitoException;
import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CRUDBackend.repository.CategoriaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService service;

    @Mock
    private CategoriaRepository repository;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoriaPai(null);
        categoria.setNome("Calças");
    }

    @Test
    void deveCadastrarComSucessoQuandoDadosDeCategoriaValida() {
        when(repository.existsByNome(categoria.getNome())).thenReturn(false);
        when(repository.save(categoria)).thenReturn(categoria);

        Categoria resultado = service.cadastrarCategoria(categoria);

        assertNotNull(resultado);
        assertEquals("Calças", resultado.getNome());
        verify(repository).save(categoria);
    }

    @Test
    void deveFalharCadastroQuandoCategoriaJaExiste() {
        when(repository.existsByNome(categoria.getNome())).thenReturn(true);

        CategoriaConflitoException excecao = assertThrows(CategoriaConflitoException.class, () -> {
            service.cadastrarCategoria(categoria);
        });

        assertEquals("Essa categoria já existe", excecao.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveTrazerListaDeCategoriasQuandoPossuirCategoriasCadastradas() {
        Categoria categoriaUm = new Categoria();
        categoriaUm.setIdCategoria(1);
        categoriaUm.setCategoriaPai(null);
        categoriaUm.setNome("Vestidos");

        Categoria categoriaDois = new Categoria();
        categoriaDois.setIdCategoria(2);
        categoriaDois.setCategoriaPai(null);
        categoriaDois.setNome("Calças");

        List<Categoria> listaDeCategorias = new ArrayList<>();
        listaDeCategorias.add(categoriaUm);
        listaDeCategorias.add(categoriaDois);

        when(repository.findAll()).thenReturn(listaDeCategorias);

        List<Categoria> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        assertEquals("Vestidos", resultado.get(0).getNome());
        assertEquals("Calças", resultado.get(1).getNome());
    }

    @Test
    void deveDarErroAoTentarTrazerListaDeCategoriasCadastradas() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            List<Categoria> categorias = service.listarTodos();
            if (categorias.isEmpty()) {
                throw new RuntimeException("Nenhuma categoria encontrada");
            }
        });

        assertEquals("Nenhuma categoria encontrada", excecao.getMessage());
    }

    @Test
    void devePassarQuandoBuscarCategoriaPorIdComIdValido() {
        when(repository.findById(categoria.getIdCategoria())).thenReturn(of(categoria));

        Categoria resultado = service.buscarPorId(1);

        assertEquals("Calças", resultado.getNome());
    }

    @Test
    void deveDarErroQuandoBuscarCategoriaPorIdComIdInvalido() {
        int idInvalido = 999;
        when(repository.findById(idInvalido)).thenReturn(empty());

        assertThrows(CategoriaNaoEncontradaException.class, () -> service.buscarPorId(idInvalido));
    }

    @Test
    void devePassarQuandoBuscarCategoriaPorNomeComNomeValido() {
        when(repository.findByNome(categoria.getNome())).thenReturn(of(categoria));

        Categoria resultado = service.buscarPorNome("Calças");

        assertEquals("Calças", resultado.getNome());
    }

    @Test
    void deveFalharQuandoBuscarCategoriaPorNomeComNomeInvalido() {
        String nomeInvalido = "CategoriaInvalida";
        when(repository.findByNome(nomeInvalido)).thenReturn(empty());

        assertThrows(CategoriaNaoEncontradaException.class, () -> {
            service.buscarPorNome(nomeInvalido);
        });
    }

    @Test
    void deveAtualizarQuandoReceberIdValido() {
        when(repository.existsById(categoria.getIdCategoria())).thenReturn(true);
        when(repository.save(categoria)).thenReturn(categoria);

        categoria.setNome("Tidosves");
        Categoria categoriaSalvar = service.atualizarPorId(categoria.getIdCategoria(), categoria);

        assertEquals(categoria.getIdCategoria(), categoriaSalvar.getIdCategoria());
        assertEquals("Tidosves", categoriaSalvar.getNome());
    }

    @Test
    void deveFalharAtualizacaoQuandoReceberIdInvalido() {
        when(repository.existsById(categoria.getIdCategoria())).thenReturn(false);

        assertThrows(CategoriaNaoEncontradaException.class, () -> {
            service.atualizarPorId(categoria.getIdCategoria(), categoria);
        });
    }

    @Test
    void deveRemoverPorIdQuandoIdValido() {
        int id = 1;

        when(repository.existsById(id)).thenReturn(true);

        service.removerPorId(id);

        verify(repository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoQuandoRemoverCategoriaComIdInexistente() {
        Integer id = 999;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(CategoriaNaoEncontradaException.class, () -> service.removerPorId(id));
    }
}
