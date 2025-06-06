package school.sptech.CRUDBackend.service;

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

    @Mock
    private Categoria entity;

    @Test
    void deveCadastrarComSucessoQuandoDadosDeCategoriaValida() {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoriaPai(null);
        categoria.setNome("Calças");

        when(repository.save(categoria)).thenReturn(categoria);

        Categoria resultado = service.cadastrarCategoria(categoria);

        assertNotNull(resultado);
        assertEquals("Calças", resultado.getNome());
        verify(repository).save(categoria);
    }

    @Test
    void deveFalharCadastroQuandoCategoriaJaExiste() {
        Categoria categoria = new Categoria();
        categoria.setNome("Calças");

        when(repository.existsByNome("Calças")).thenReturn(true);

        Categoria resultado = service.cadastrarCategoria(categoria);

        CategoriaConflitoException excecao = assertThrows(CategoriaConflitoException.class, () -> {
            service.cadastrarCategoria(categoria);
        });

        assertEquals("Essa categoria já existe", excecao.getMessage());

        verify(repository, never()).save(any());
    }

    @Test
    void deveTrazerListaDeCategoriasQuandoPossuirCategoriasCadastradas() {
        List<Categoria> listaDeCategorias = new ArrayList<>();

        Categoria categoriaUm = new Categoria();
        categoriaUm.setIdCategoria(1);
        categoriaUm.setCategoriaPai(null);
        categoriaUm.setNome("Calças");
        listaDeCategorias.add(categoriaUm);

        Categoria categoriaDois = new Categoria();
        categoriaDois.setIdCategoria(1);
        categoriaDois.setCategoriaPai(null);
        categoriaDois.setNome("Vestidos");
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
            service.listarTodos();
        });

        assertEquals("Nenhuma categoria encontrada", excecao.getMessage());
    }

    @Test
    void devePassarQuandoBuscarCategoriaPorIdComIdVálido() {
        // Given
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoriaPai(null);
        categoria.setNome("Vestidos");
        // When
            when(repository.findById(categoria.getIdCategoria())).thenReturn(of(categoria));
        // Then
            Categoria resultado = service.buscarPorId(1);
        // Assert
            assertEquals("Vestidos", resultado.getNome());
    }

    @Test
    void DeveDarErroQuandoBuscarCategoriaPorIdComIdInvalido() {
        // Given
        int idInvalido = 999;
        when(repository.findById(idInvalido)).thenReturn(of(new Categoria()));
        // When

        Categoria resultado = service.buscarPorId(idInvalido);
        // Then
        assertNull(resultado.getNome());
    }

    @Test
    void devePassarQuandoBuscarCategoriaPorNomeComNomeVálido() {
        // Given
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoriaPai(null);
        categoria.setNome("Vestidos");
        // When
        when(repository.findByNome(categoria.getNome())).thenReturn(of(categoria));
        // Then
        Categoria resultado = service.buscarPorNome("Vestdos");
        // Assert
        assertEquals("Vestidos", resultado.getNome());
    }

    @Test
    void deveFalharQuandoBuscarCategoriaPorNomeComNomeInválido() {
        String nomeInvalido = "CategoriaInvalida";
        when(repository.findByNome(nomeInvalido)).thenReturn(empty());

        Categoria resultado = service.buscarPorNome(nomeInvalido);

        assertNull(resultado);
    }

    @Test
    void deveAtualizarQuandoReceberIdValido() {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoriaPai(null);
        categoria.setNome("Vestidos");

        when(repository.existsById(categoria.getIdCategoria())).thenReturn(true);
        categoria.setNome("Tidosves");
        Categoria categoriaSalvar = service.atualizarPorId(categoria.getIdCategoria(), categoria);
        when(repository.save(categoriaSalvar));

        assertEquals(categoria.getIdCategoria(), categoriaSalvar.getIdCategoria());
        assertEquals(categoria.getNome(), categoriaSalvar.getNome());
    }

    @Test
    void deveFalharAtualizacaoQuandoReceberIdInvalido() {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(999);
        categoria.setCategoriaPai(null);
        categoria.setNome("Vestidos");

        when(repository.existsById(categoria.getIdCategoria())).thenReturn(false);

        assertThrows(CategoriaNaoEncontradaException.class, () -> {
            service.atualizarPorId(categoria.getIdCategoria(), categoria);
        });
    }

    @Test
    void deveRemoverPorIdQuandoIdValido() {
        Integer id = 1;

        when(repository.existsById(id)).thenReturn(true);

        service.removerPorId(id);

        verify(repository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoQuandoRemoverCategoriaComIdInexistente() {
        // Given
        Integer id = 999;
        when(repository.existsById(id)).thenReturn(false);

        // When & Then
        assertThrows(CategoriaNaoEncontradaException.class, () -> service.removerPorId(id));
    }

}