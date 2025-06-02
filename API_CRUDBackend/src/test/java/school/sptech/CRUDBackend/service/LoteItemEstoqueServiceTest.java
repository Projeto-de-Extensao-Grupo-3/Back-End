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
import school.sptech.CRUDBackend.repository.LoteItemEstoqueRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoteItemEstoqueServiceTest {

    @Mock
    private LoteItemEstoqueRepository loteItemEstoqueRepository;

    @Mock
    private ItemEstoqueService itemEstoqueService;

    @Mock
    private List<Observer> observadores = new ArrayList<>();

    @InjectMocks
    private LoteItemEstoqueService loteItemEstoqueService;

    private LoteItemEstoque loteItemEstoque;

    private Alerta alerta;

    private ItemEstoque itemEstoque;

    private Set<Categoria> caracteristicas = new HashSet<>();

    private Set<ConfeccaoRoupa> confeccaoRoupas = new HashSet<>();

    private Set<Permissao> listaPermissaoTeste = new HashSet<>();

    @BeforeEach
    void setUp(){
        Categoria tecido = new Categoria(1, "Tecido", null);
        Categoria categoriaPai = new Categoria(2, "Vestido", null);
        Categoria categoriaFilho = new Categoria(3, "Vestido Florido", categoriaPai);
        Categoria caracteristica1 = new Categoria(4, "Jeans", tecido);
        Categoria caracteristica2 = new Categoria(5, "Algodão", tecido);
        caracteristicas.add(caracteristica1);
        caracteristicas.add(caracteristica2);
        Prateleira prateleira = new Prateleira(1, "1A");
        itemEstoque = new ItemEstoque(1, "Vestido com Flores Azuis", "Tamanho Unico", 1.5,
                10.0, 50.0, categoriaFilho, caracteristicas, prateleira, confeccaoRoupas);

    Parceiro parceiro = new Parceiro(1, "Fabricante", "Fornecedor de Jeans", "000", "fabricante@gmail.com", "R. Haddock Lobo, 595", "teste1");
        Permissao permissaoTeste1 = new Permissao(1, "Cadastrar Funcionario");
        listaPermissaoTeste.add(permissaoTeste1);
        Permissao permissaoTeste2 = new Permissao(2, "Visualizar Dashboard");
        listaPermissaoTeste.add(permissaoTeste2);
        Funcionario funcionario = new Funcionario(1, "Leandro", "123456789", "11 977839256",
                "leandro@gmail.com", "123", listaPermissaoTeste);
        Lote lote = new Lote(1, "Lote de Tecido Algodão", "10/06/2025", parceiro, funcionario);
    loteItemEstoque = new LoteItemEstoque(1, 10.0, 25.00, itemEstoque, lote);
    }

    @Test
    @DisplayName("Deve cadastrar novo Lote de Item com Sucesso")
    void deveCadastrarNovoLoteDeItemComSucesso(){
        // Given

        // When
        when(loteItemEstoqueRepository.save(any())).thenReturn(loteItemEstoque);

        //Then
        LoteItemEstoque resultado = loteItemEstoqueService.cadastrarLoteItemEstoque(loteItemEstoque);

        //Assert
        verify(loteItemEstoqueRepository, times(1)).save(any());
        assertEquals(loteItemEstoque, resultado);
    }



}