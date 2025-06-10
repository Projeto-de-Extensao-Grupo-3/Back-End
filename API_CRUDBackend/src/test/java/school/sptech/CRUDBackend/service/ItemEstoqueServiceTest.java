
package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import school.sptech.CRUDBackend.entity.ConfeccaoRoupa;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.exception.itensEstoque.ItemEstoqueConflitoException;
import school.sptech.CRUDBackend.exception.itensEstoque.ItemEstoqueNaoEncontradoException;
import school.sptech.CRUDBackend.repository.ItemEstoqueRepository;

import java.util.*;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ItemEstoqueServiceTest {

    @Mock
    private ItemEstoqueRepository itemEstoqueRepository;

    @InjectMocks
    private ItemEstoqueService itemEstoqueService;

    private ItemEstoque item;

    @BeforeEach
    void setUp() {
        item = new ItemEstoque();
        item.setIdItemEstoque(1);
        item.setDescricao("Camisa");
        item.setPreco(100.0);
    }

    @Test
    void testCadastrarItemEstoque_Positive() {
        when(itemEstoqueRepository.existsByDescricao(item.getDescricao())).thenReturn(false);
        when(itemEstoqueRepository.save(item)).thenReturn(item);

        ItemEstoque result = itemEstoqueService.cadastrarItemEstoque(item);

        assertEquals(item, result);
        verify(itemEstoqueRepository).save(item);
    }

    @Test
    void testCadastrarItemEstoque_Negative() {
        when(itemEstoqueRepository.existsByDescricao(item.getDescricao())).thenReturn(true);

        assertThrows(ItemEstoqueConflitoException.class, () -> itemEstoqueService.cadastrarItemEstoque(item));
    }

    @Test
    void testCadastrarTecidosDaRoupa_Positive() {
        Set<ConfeccaoRoupa> tecidos = new HashSet<>();
        when(itemEstoqueRepository.existsById(1)).thenReturn(true);
        when(itemEstoqueRepository.findById(1)).thenReturn(of(item));
        when(itemEstoqueRepository.save(any())).thenReturn(item);

        ItemEstoque result = itemEstoqueService.cadastrarTecidosDaRoupa(1, tecidos);

        assertEquals(item, result);
        verify(itemEstoqueRepository).save(item);
    }

    @Test
    void testCadastrarTecidosDaRoupa_Negative() {
        when(itemEstoqueRepository.existsById(1)).thenReturn(false);

        assertThrows(ItemEstoqueNaoEncontradoException.class, () -> itemEstoqueService.cadastrarTecidosDaRoupa(1, new HashSet<>()));
    }

    @Test
    void testVerificarTodosItemEstoque() {
        List<ItemEstoque> lista = List.of(item);
        when(itemEstoqueRepository.findAll()).thenReturn(lista);

        List<ItemEstoque> result = itemEstoqueService.verificarTodosItemEstoque();

        assertEquals(lista, result);
    }

    @Test
    void testBuscarItemEstoquePorId_Positive() {
        when(itemEstoqueRepository.findById(1)).thenReturn(of(item));

        ItemEstoque result = itemEstoqueService.buscarItemEstoquePorId(1);

        assertEquals(item, result);
    }

    @Test
    void testBuscarItemEstoquePorId_Negative() {
        when(itemEstoqueRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ItemEstoqueNaoEncontradoException.class, () -> itemEstoqueService.buscarItemEstoquePorId(1));
    }

    @Test
    void testAtualizarItemEstoquePorId_Positive() {
        when(itemEstoqueRepository.existsById(1)).thenReturn(true);
        when(itemEstoqueRepository.save(item)).thenReturn(item);

        ItemEstoque result = itemEstoqueService.atualizarItemEstoquePorId(1, item);

        assertEquals(item, result);
    }

    @Test
    void testAtualizarItemEstoquePorId_Negative() {
        when(itemEstoqueRepository.existsById(1)).thenReturn(false);

        assertThrows(ItemEstoqueNaoEncontradoException.class, () -> itemEstoqueService.atualizarItemEstoquePorId(1, item));
    }

    @Test
    void testRemoverPorId_Positive() {
        when(itemEstoqueRepository.existsById(1)).thenReturn(true);

        itemEstoqueService.removerPorId(1);

        verify(itemEstoqueRepository).deleteById(1);
    }

    @Test
    void testRemoverPorId_Negative() {
        when(itemEstoqueRepository.existsById(1)).thenReturn(false);

        assertThrows(ItemEstoqueNaoEncontradoException.class, () -> itemEstoqueService.removerPorId(1));
    }

    @Test
    void testAtualizarQuantidade() {
        when(itemEstoqueRepository.existsById(1)).thenReturn(true);
        when(itemEstoqueRepository.save(item)).thenReturn(item);

        itemEstoqueService.atualizarQuantidade(item);

        verify(itemEstoqueRepository).save(item);
    }

    @Test
    void testBuscarItemEstoquePorDescricao() {
        List<ItemEstoque> lista = List.of(item);
        when(itemEstoqueRepository.findByDescricaoContainsIgnoreCase("camisa")).thenReturn(lista);

        List<ItemEstoque> result = itemEstoqueService.buscarItemEstoquePorDescricao("camisa");

        assertEquals(lista, result);
    }

    @Test
    void testBuscarItemEstoquePorTipo() {
        List<ItemEstoque> lista = List.of(item);
        when(itemEstoqueRepository.findByTipo("roupa")).thenReturn(lista);

        List<ItemEstoque> result = itemEstoqueService.buscarItemEstoquePorTipo("roupa");

        assertEquals(lista, result);
    }

    @Test
    void testCalcularCustoProducao_Positive() {
        when(itemEstoqueRepository.existsById(1)).thenReturn(true);
        when(itemEstoqueRepository.calcularCustoProducao(1)).thenReturn(250.0);

        Double result = itemEstoqueService.calcularCustoProducao(1);

        assertEquals(250.0, result);
    }

    @Test
    void testCalcularCustoProducao_Negative() {
        when(itemEstoqueRepository.existsById(1)).thenReturn(false);

        assertThrows(ItemEstoqueNaoEncontradoException.class, () -> itemEstoqueService.calcularCustoProducao(1));
    }
}
