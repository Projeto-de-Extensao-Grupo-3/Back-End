package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.*;
import school.sptech.CRUDBackend.Model.itemEstoque.Observer;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.entity.SaidaEstoque;
import school.sptech.CRUDBackend.exception.SaidaEstoque.SaidaEstoqueNaoEncontradoException;
import school.sptech.CRUDBackend.repository.SaidaEstoqueRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@DisplayName("Saída Estoque Service Tests")
class SaidaEstoqueServiceTest {

    // Constants for test data
    private static final Integer VALID_ID = 1;
    private static final Integer INVALID_ID = 999;
    private static final Integer VALID_LOTE_ID = 1;
    private static final Double QUANTIDADE_INICIAL = 50.0;
    private static final Double QUANTIDADE_SAIDA = 10.0;
    private static final Double QUANTIDADE_FINAL_ESPERADA = 40.0;
    private static final Double QUANTIDADE_SAIDA_ANTIGA = 5.0;
    private static final Double QUANTIDADE_SAIDA_NOVA = 8.0;
    private static final Double QUANTIDADE_ATUALIZACAO = 30.0;
    private static final Double QUANTIDADE_FINAL_ATUALIZACAO = 27.0;
    private static final Double QUANTIDADE_REMOCAO = 20.0;
    private static final String MOTIVO_VALIDO = "venda";
    private static final LocalDate DATA_VALIDA = LocalDate.now();
    private static final int LISTA_COM_UM_ITEM = 1;

    @InjectMocks
    private SaidaEstoqueService saidaEstoqueService;

    @Mock
    private SaidaEstoqueRepository saidaEstoqueRepository;

    @Mock
    private LoteItemEstoqueService loteItemEstoqueService;

    @Mock
    private Observer observador;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        saidaEstoqueService.adicionarObservador(observador);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }

    @Nested
    @DisplayName("Cadastro de Saída de Estoque")
    class CadastroSaidaEstoqueTests {

        @Test
        @DisplayName("Deve cadastrar saída atualizando quantidade do item estoque")
        void deveCadastrarSaidaAtualizandoQtdItemEstoque() {
            // Arrange
            SaidaEstoque saidaEstoque = createSaidaEstoqueComLote();
            setupSuccessfulCadastro(saidaEstoque);

            // Act
            SaidaEstoque resultado = saidaEstoqueService.cadastrar(saidaEstoque);

            // Assert
            assertSaidaCadastradaComSucesso(resultado, saidaEstoque);
            verifyCadastroInteractions(saidaEstoque);
        }

        private void setupSuccessfulCadastro(SaidaEstoque saidaEstoque) {
            when(loteItemEstoqueService.buscarLoteItemEstoquePorId(VALID_LOTE_ID))
                    .thenReturn(saidaEstoque.getLoteItemEstoque());
            when(saidaEstoqueRepository.save(any())).thenReturn(saidaEstoque);
        }

        private void assertSaidaCadastradaComSucesso(SaidaEstoque resultado, SaidaEstoque expected) {
            assertEquals(expected, resultado);
            assertEquals(QUANTIDADE_FINAL_ESPERADA,
                    expected.getLoteItemEstoque().getItemEstoque().getQtdArmazenado());
        }

        private void verifyCadastroInteractions(SaidaEstoque saidaEstoque) {
            verify(observador).atualizarQuantidade(saidaEstoque.getLoteItemEstoque().getItemEstoque());
            verify(saidaEstoqueRepository).save(saidaEstoque);
        }
    }

    @Nested
    @DisplayName("Busca de Saída por ID")
    class BuscaSaidaPorIdTests {

        @Test
        @DisplayName("Deve buscar saída por ID quando existe")
        void deveBuscarSaidaPorIdExistente() {
            // Arrange
            SaidaEstoque saidaEsperada = createSaidaEstoqueComId(VALID_ID);
            setupBuscaPorIdExistente(saidaEsperada);

            // Act
            SaidaEstoque resultado = saidaEstoqueService.buscarPorId(VALID_ID);

            // Assert
            assertEquals(saidaEsperada, resultado);
        }

        @Test
        @DisplayName("Deve lançar exceção quando buscar ID inexistente")
        void deveLancarExcecaoQuandoBuscarIdInexistente() {
            // Arrange
            setupBuscaPorIdInexistente();

            // Act & Assert
            assertThrows(SaidaEstoqueNaoEncontradoException.class,
                    () -> saidaEstoqueService.buscarPorId(INVALID_ID));
        }

        private void setupBuscaPorIdExistente(SaidaEstoque saidaEstoque) {
            when(saidaEstoqueRepository.findById(VALID_ID))
                    .thenReturn(Optional.of(saidaEstoque));
        }

        private void setupBuscaPorIdInexistente() {
            when(saidaEstoqueRepository.findById(anyInt()))
                    .thenReturn(Optional.empty());
        }
    }

    @Nested
    @DisplayName("Busca de Saída por Motivo")
    class BuscaSaidaPorMotivoTests {

        @Test
        @DisplayName("Deve buscar saída por motivo com resultado")
        void deveBuscarSaidaPorMotivoComResultado() {
            // Arrange
            List<SaidaEstoque> saidasEsperadas = createListaSaidasEstoque();
            setupBuscaPorMotivoComResultado(saidasEsperadas);

            // Act
            List<SaidaEstoque> resultado = saidaEstoqueService.buscarPorMotivo(MOTIVO_VALIDO);

            // Assert
            assertBuscaPorMotivoComSucesso(resultado, saidasEsperadas);
        }

        @Test
        @DisplayName("Deve lançar exceção quando buscar por motivo vazio")
        void deveLancarExcecaoQuandoBuscarPorMotivoVazio() {
            // Arrange
            setupBuscaPorMotivoVazio();

            // Act & Assert
            assertThrows(SaidaEstoqueNaoEncontradoException.class,
                    () -> saidaEstoqueService.buscarPorMotivo(MOTIVO_VALIDO));
        }

        private void setupBuscaPorMotivoComResultado(List<SaidaEstoque> saidas) {
            when(saidaEstoqueRepository.findByMotivoSaida(MOTIVO_VALIDO))
                    .thenReturn(saidas);
        }

        private void setupBuscaPorMotivoVazio() {
            when(saidaEstoqueRepository.findByMotivoSaida(MOTIVO_VALIDO))
                    .thenReturn(Collections.emptyList());
        }

        private void assertBuscaPorMotivoComSucesso(List<SaidaEstoque> resultado, List<SaidaEstoque> expected) {
            assertEquals(LISTA_COM_UM_ITEM, resultado.size());
            assertEquals(expected.get(0), resultado.get(0));
        }
    }

    @Nested
    @DisplayName("Busca de Saída por Data")
    class BuscaSaidaPorDataTests {

        @Test
        @DisplayName("Deve buscar saída por data com resultado")
        void deveBuscarSaidaPorDataComResultado() {
            // Arrange
            List<SaidaEstoque> saidasEsperadas = createListaSaidasEstoque();
            setupBuscaPorDataComResultado(saidasEsperadas);

            // Act
            List<SaidaEstoque> resultado = saidaEstoqueService.buscarPorData(DATA_VALIDA);

            // Assert
            assertBuscaPorDataComSucesso(resultado, saidasEsperadas);
        }

        @Test
        @DisplayName("Deve lançar exceção quando buscar por data vazia")
        void deveLancarExcecaoQuandoBuscarPorDataVazia() {
            // Arrange
            setupBuscaPorDataVazia();

            // Act & Assert
            assertThrows(SaidaEstoqueNaoEncontradoException.class,
                    () -> saidaEstoqueService.buscarPorData(DATA_VALIDA));
        }

        private void setupBuscaPorDataComResultado(List<SaidaEstoque> saidas) {
            when(saidaEstoqueRepository.findByData(DATA_VALIDA))
                    .thenReturn(saidas);
        }

        private void setupBuscaPorDataVazia() {
            when(saidaEstoqueRepository.findByData(any(LocalDate.class)))
                    .thenReturn(Collections.emptyList());
        }

        private void assertBuscaPorDataComSucesso(List<SaidaEstoque> resultado, List<SaidaEstoque> expected) {
            assertEquals(LISTA_COM_UM_ITEM, resultado.size());
            assertEquals(expected.get(0), resultado.get(0));
        }
    }

    @Nested
    @DisplayName("Atualização de Saída de Estoque")
    class AtualizacaoSaidaEstoqueTests {

        @Test
        @DisplayName("Deve atualizar saída com nova quantidade")
        void deveAtualizarSaidaComNovaQuantidade() {
            // Arrange
            SaidaEstoque saidaAntiga = createSaidaAntigaParaAtualizacao();
            SaidaEstoque saidaNova = createSaidaNovaParaAtualizacao();
            setupAtualizacaoComSucesso(saidaAntiga, saidaNova);

            // Act
            SaidaEstoque resultado = saidaEstoqueService.atualizarPorId(VALID_ID, saidaNova);

            // Assert
            assertAtualizacaoComSucesso(saidaAntiga);
            verifyAtualizacaoInteractions(saidaAntiga);
        }

        private void setupAtualizacaoComSucesso(SaidaEstoque antiga, SaidaEstoque nova) {
            when(saidaEstoqueRepository.existsById(VALID_ID)).thenReturn(true);
            when(saidaEstoqueRepository.findById(VALID_ID)).thenReturn(Optional.of(antiga));
            when(loteItemEstoqueService.buscarLoteItemEstoquePorId(VALID_LOTE_ID))
                    .thenReturn(antiga.getLoteItemEstoque());
            when(saidaEstoqueRepository.save(any())).thenReturn(nova);
        }

        private void assertAtualizacaoComSucesso(SaidaEstoque saidaAntiga) {
            assertEquals(QUANTIDADE_FINAL_ATUALIZACAO,
                    saidaAntiga.getLoteItemEstoque().getItemEstoque().getQtdArmazenado());
        }

        private void verifyAtualizacaoInteractions(SaidaEstoque saidaAntiga) {
            verify(observador).atualizarQuantidade(saidaAntiga.getLoteItemEstoque().getItemEstoque());
        }
    }

    @Nested
    @DisplayName("Remoção de Saída de Estoque")
    class RemocaoSaidaEstoqueTests {

        @Test
        @DisplayName("Deve remover saída por ID existente")
        void deveRemoverSaidaPorIdExistente() {
            // Arrange
            setupRemocaoComSucesso();

            // Act
            saidaEstoqueService.removerPorId(VALID_ID);

            // Assert
            verifyRemocaoInteractions();
        }

        @Test
        @DisplayName("Deve lançar exceção ao tentar remover ID inexistente")
        void deveLancarExcecaoAoTentarRemoverIdInexistente() {
            // Arrange
            setupRemocaoIdInexistente();

            // Act & Assert
            assertThrows(SaidaEstoqueNaoEncontradoException.class,
                    () -> saidaEstoqueService.removerPorId(VALID_ID));
        }

        private void setupRemocaoComSucesso() {
            when(saidaEstoqueRepository.existsById(VALID_ID)).thenReturn(true);
        }

        private void setupRemocaoIdInexistente() {
            when(saidaEstoqueRepository.existsById(VALID_ID)).thenReturn(false);
        }

        private void verifyRemocaoInteractions() {
            verify(saidaEstoqueRepository).deleteById(VALID_ID);
        }
    }

    // Factory methods for test data creation
    private SaidaEstoque createSaidaEstoqueComLote() {
        ItemEstoque item = createItemEstoque(QUANTIDADE_INICIAL);
        LoteItemEstoque lote = createLoteItemEstoque(VALID_LOTE_ID, item);
        SaidaEstoque saida = createSaidaEstoque(null, QUANTIDADE_SAIDA);
        saida.setLoteItemEstoque(lote);
        return saida;
    }

    private SaidaEstoque createSaidaEstoqueComId(Integer id) {
        SaidaEstoque saida = createSaidaEstoque(id, QUANTIDADE_SAIDA);
        return saida;
    }

    private SaidaEstoque createSaidaAntigaParaAtualizacao() {
        ItemEstoque item = createItemEstoque(QUANTIDADE_ATUALIZACAO);
        LoteItemEstoque lote = createLoteItemEstoque(VALID_LOTE_ID, item);
        SaidaEstoque saida = createSaidaEstoque(VALID_ID, QUANTIDADE_SAIDA_ANTIGA);
        saida.setLoteItemEstoque(lote);
        return saida;
    }

    private SaidaEstoque createSaidaNovaParaAtualizacao() {
        ItemEstoque item = createItemEstoque(QUANTIDADE_ATUALIZACAO);
        LoteItemEstoque lote = createLoteItemEstoque(VALID_LOTE_ID, item);
        SaidaEstoque saida = createSaidaEstoque(null, QUANTIDADE_SAIDA_NOVA);
        saida.setLoteItemEstoque(lote);
        return saida;
    }

    private List<SaidaEstoque> createListaSaidasEstoque() {
        return List.of(createSaidaEstoque(VALID_ID, QUANTIDADE_SAIDA));
    }

    private SaidaEstoque createSaidaEstoque(Integer id, Double quantidade) {
        SaidaEstoque saida = new SaidaEstoque();
        saida.setIdSaidaEstoque(id);
        saida.setQtdSaida(quantidade);
        return saida;
    }

    private ItemEstoque createItemEstoque(Double quantidade) {
        ItemEstoque item = new ItemEstoque();
        item.setQtdArmazenado(quantidade);
        return item;
    }

    private LoteItemEstoque createLoteItemEstoque(Integer id, ItemEstoque item) {
        LoteItemEstoque lote = new LoteItemEstoque();
        lote.setIdLoteItemEstoque(id);
        lote.setItemEstoque(item);
        return lote;
    }
}