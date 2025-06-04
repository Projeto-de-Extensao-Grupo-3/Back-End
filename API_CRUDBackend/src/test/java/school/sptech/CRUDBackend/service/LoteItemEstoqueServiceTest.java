package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.CRUDBackend.Model.itemEstoque.Observer;
import school.sptech.CRUDBackend.entity.*;
import school.sptech.CRUDBackend.exception.Lote.LoteConflitoException;
import school.sptech.CRUDBackend.exception.Lote.LoteNaoEncontradoException;
import school.sptech.CRUDBackend.exception.LoteItemEstoque.LoteItemEstoqueNaoEncontradoException;
import school.sptech.CRUDBackend.repository.LoteItemEstoqueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoteItemEstoqueServiceTest {

    @Mock
    private LoteItemEstoqueRepository loteItemEstoqueRepository;

    @Mock
    private ItemEstoqueService itemEstoqueService;

    @InjectMocks
    private LoteItemEstoqueService loteItemEstoqueService;

    private LoteItemEstoque loteItemEstoque;

    private Alerta alerta;

    private ItemEstoque itemEstoque;

    private Lote lote;

    private Set<Categoria> caracteristicas = new HashSet<>();

    private Set<ConfeccaoRoupa> confeccaoRoupas = new HashSet<>();

    private Set<Permissao> listaPermissaoTeste = new HashSet<>();

    @BeforeEach
    void setUp(){
        loteItemEstoqueService.adicionarObservador(itemEstoqueService);
        Categoria tecido = new Categoria(1, "Tecido", null);
        Categoria categoriaPai = new Categoria(2, "Vestido", null);
        Categoria categoriaFilho = new Categoria(3, "Vestido Florido", categoriaPai);
        Categoria caracteristica1 = new Categoria(4, "Jeans", tecido);
        Categoria caracteristica2 = new Categoria(5, "Algodão", tecido);
        caracteristicas.add(caracteristica1);
        caracteristicas.add(caracteristica2);
        Prateleira prateleira = new Prateleira(1, "1A");
        Imagem imagem = new Imagem(1, "afkasmfoa");
        itemEstoque = new ItemEstoque(1, "Vestido com Flores Azuis", "Tamanho Unico", 1.5,
                10.0, 50.0, categoriaFilho, caracteristicas, prateleira, confeccaoRoupas, 100.0, imagem);

    Parceiro parceiro = new Parceiro(1, "Fabricante", "Fornecedor de Jeans", "000", "fabricante@gmail.com", "R. Haddock Lobo, 595", "teste1");
        Permissao permissaoTeste1 = new Permissao(1, "Cadastrar Funcionario");
        listaPermissaoTeste.add(permissaoTeste1);
        Permissao permissaoTeste2 = new Permissao(2, "Visualizar Dashboard");
        listaPermissaoTeste.add(permissaoTeste2);
        Funcionario funcionario = new Funcionario(1, "Leandro", "123456789", "11 977839256",
                "leandro@gmail.com", "123", listaPermissaoTeste);
        lote = new Lote(1, "Lote de Tecido Algodão", "10/06/2025", parceiro, funcionario);
    loteItemEstoque = new LoteItemEstoque(1, 10.0, 25.00, itemEstoque, lote);
    }

    @Test
    @DisplayName("Deve cadastrar novo Lote de Item com Sucesso")
    void deveCadastrarNovoLoteDeItemComSucesso(){
        // Given

        // When
        when(loteItemEstoqueRepository.save(any())).thenReturn(loteItemEstoque);
        when(itemEstoqueService.buscarItemEstoquePorId(anyInt())).thenReturn(itemEstoque);

        //Then
        LoteItemEstoque resultado = loteItemEstoqueService.cadastrarLoteItemEstoque(loteItemEstoque);

        //Assert
        verify(loteItemEstoqueRepository, times(1)).save(any());
        assertEquals(loteItemEstoque, resultado);
    }

    @Test
    @DisplayName("Deve retornar lista com Lote de Item Estoque cadastrado")
    void deveListarLoteDeItemEstoqueCadastrados(){
        //Given
        List<LoteItemEstoque> lotes = List.of(loteItemEstoque);

        //When
        when(loteItemEstoqueRepository.findAll()).thenReturn(lotes);

        //Then
        List<LoteItemEstoque> resultado = loteItemEstoqueService.listarTodos();

        //Assert
        assertEquals(lotes, resultado);
        assertEquals(lotes.size(), resultado.size());
    }

    @Test
    @DisplayName("Deve retornar uma lista de Lote De Item Estoque vazia")
    void deveRetornarListaDeLoteItemEstoqueVazia(){
        //Given

        //When
        when(loteItemEstoqueRepository.findAll()).thenReturn(Collections.emptyList());

        //Then
        List<LoteItemEstoque> resultado = loteItemEstoqueService.listarTodos();

        //Assert
        assertTrue(resultado.isEmpty(), "A lista deve estar vazia");
        assertEquals(0, resultado.size());
    }

    @Test
    @DisplayName("Deve retornar Lote de Item Estoque com ID informado")
    void deveRetornarLoteDeItemEstoqueComIdInformado(){
        //Given

        //When
        when(loteItemEstoqueRepository.findById(anyInt())).thenReturn(Optional.of(loteItemEstoque));

        //Then
        LoteItemEstoque resultado = loteItemEstoqueService.buscarLoteItemEstoquePorId(loteItemEstoque.getIdLoteItemEstoque());

        //Assert
        assertEquals(loteItemEstoque, resultado);
        assertEquals(loteItemEstoque.getIdLoteItemEstoque(), resultado.getIdLoteItemEstoque());
    }

    @Test
    @DisplayName("Deve lancar excessao para quando buscar por ID de Lote de Item Estoque for invalido")
    void deveLancarExcessaoParaIdLoteItemEstoqueInvalido(){
        //Given

        //When
        when(loteItemEstoqueRepository.findById(anyInt())).thenReturn(Optional.empty());

        //Then

        //Assert
        assertThrows(LoteItemEstoqueNaoEncontradoException.class, () -> loteItemEstoqueService.buscarLoteItemEstoquePorId(loteItemEstoque.getIdLoteItemEstoque()));
    }

    @Test
    @DisplayName("Deve atualizar Lote de Item Estoque com novos dados e ID informado")
    void deveAtualizarLoteItemEstoqueComNovosDados(){
        //Given
        LoteItemEstoque loteItemEstoqueAtualizar = new LoteItemEstoque(null, 60.0, 30.00, itemEstoque, lote);
        Double qtdAtualizada = loteItemEstoqueAtualizar.getQtdItem() - loteItemEstoque.getQtdItem();

        //When
        when(loteItemEstoqueRepository.existsById(anyInt())).thenReturn(true);
        when(loteItemEstoqueRepository.findById(anyInt())).thenReturn(Optional.of(loteItemEstoque));
        when(itemEstoqueService.buscarItemEstoquePorId(anyInt())).thenReturn(itemEstoque);
        when(loteItemEstoqueRepository.save(any())).thenReturn(loteItemEstoqueAtualizar);

        //Then
        LoteItemEstoque resultado = loteItemEstoqueService.atualizarLoteItemEstoquePorId(loteItemEstoque.getIdLoteItemEstoque(), loteItemEstoqueAtualizar);

        //Assert
        verify(loteItemEstoqueRepository, times(1)).save(any());
        assertEquals(loteItemEstoqueAtualizar, resultado);
        assertEquals(loteItemEstoqueAtualizar.getQtdItem(), resultado.getQtdItem());
        assertEquals(loteItemEstoqueAtualizar.getPreco(), resultado.getPreco());
    }

    @Test
    @DisplayName("Deve lancar excessao ao tentar atualizar ID de Lote Item Estoque invalido")
    void deveLancarExcessaoParaIdNaoEncontrado(){
        //Given
        LoteItemEstoque loteItemEstoqueAtualizar = new LoteItemEstoque(null, 60.0, 30.00, itemEstoque, lote);

        //When
        when(loteItemEstoqueRepository.existsById(anyInt())).thenReturn(false);

        //Then

        //Assert
        assertThrows(LoteItemEstoqueNaoEncontradoException.class, () -> loteItemEstoqueService.atualizarLoteItemEstoquePorId(anyInt(), loteItemEstoqueAtualizar));
    }

    @Test
    @DisplayName("Deve remover Lote de Item Estoque com ID informado")
    void deveRemoverLoteItemEstoqueComIdInformado(){
        //Given

        //When
        when(loteItemEstoqueRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(loteItemEstoqueRepository).deleteById(anyInt());

        //Then
        loteItemEstoqueService.removerPorId(loteItemEstoque.getIdLoteItemEstoque());

        //Assert
        verify(loteItemEstoqueRepository, times(1)).deleteById(loteItemEstoque.getIdLoteItemEstoque());
    }

    @Test
    @DisplayName("Deve lancar excessao ao tentar remover Lote de Item Estoque com ID invalido")
    void deveLancarExcessaoParaIdInvalidoAoTentarRemover(){
        //Given

        //When
        when(loteItemEstoqueRepository.existsById(anyInt())).thenReturn(false);

        //Then

        //Assert
        assertThrows(LoteItemEstoqueNaoEncontradoException.class, () -> loteItemEstoqueService.removerPorId(loteItemEstoque.getIdLoteItemEstoque()));
        verify(loteItemEstoqueRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Deve notificar os Observers efetivamente")
    void deveNotificarObserver(){
        loteItemEstoqueService.notificarObservers(itemEstoque);
        verify(itemEstoqueService, times(1)).atualizarQuantidade(itemEstoque);
    }


}