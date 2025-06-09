package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.MockitoAnnotations;
import school.sptech.CRUDBackend.entity.CorteTecido;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.exception.corteTecido.CorteTecidoNaoEncontradoException;
import school.sptech.CRUDBackend.repository.CorteTecidoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Corte Tecido Service Tests")
class CorteTecidoServiceTest {

    // Constants for test data
    private static final Integer VALID_ID = 1;
    private static final Integer INVALID_ID = 99;
    private static final String VALID_INICIO = "2025-06-01T10:00";
    private static final String VALID_TERMINO = "2025-06-01T12:00";
    private static final String FUNCIONARIO_NOME = "João Silva";
    private static final Integer FUNCIONARIO_ID = 1;
    private static final Integer LOTE_ID = 1;

    private CorteTecidoRepository corteTecidoRepository;
    private CorteTecidoService corteTecidoService;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        corteTecidoRepository = mock(CorteTecidoRepository.class);
        corteTecidoService = new CorteTecidoService(corteTecidoRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }

    @Nested
    @DisplayName("Cadastro de Corte Tecido")
    class CadastroCorteTecidoTests {

        @Test
        @DisplayName("Deve cadastrar corte tecido com sucesso quando dados são válidos")
        void deveCadastrarCorteTecidoComSucesso_QuandoDadosValidos() {
            // Arrange
            CorteTecido corteTecido = createValidCorteTecido();
            setupSuccessfulRegistration(corteTecido);

            // Act
            CorteTecido resultado = corteTecidoService.cadastrarCorteTecido(corteTecido);

            // Assert
            assertCorteTecidoRegisteredSuccessfully(resultado);
            verifyRegistrationInteractions(corteTecido);
        }

        private void setupSuccessfulRegistration(CorteTecido corteTecido) {
            when(corteTecidoRepository.save(corteTecido)).thenReturn(corteTecido);
        }

        private void assertCorteTecidoRegisteredSuccessfully(CorteTecido resultado) {
            assertNotNull(resultado);
            assertEquals(VALID_INICIO, resultado.getInicio());
            assertEquals(VALID_TERMINO, resultado.getTermino());
            assertEquals(VALID_ID, resultado.getIdCorteTecido());
        }

        private void verifyRegistrationInteractions(CorteTecido corteTecido) {
            verify(corteTecidoRepository, times(1)).save(corteTecido);
        }
    }

    @Nested
    @DisplayName("Consulta de Cortes Tecido")
    class ConsultaCorteTecidoTests {

        @Test
        @DisplayName("Deve listar todos os cortes tecido com sucesso quando existem registros")
        void deveListarTodosOsCortesTecido_QuandoExistemRegistros() {
            // Arrange
            List<CorteTecido> expectedList = createCorteTecidoList();
            setupSuccessfulListRetrieval(expectedList);

            // Act
            List<CorteTecido> resultado = corteTecidoService.verificarTodosCortesTecido();

            // Assert
            assertListReturnedSuccessfully(resultado, expectedList);
            verifyListRetrievalInteractions();
        }

        @Test
        @DisplayName("Deve buscar corte tecido por ID com sucesso quando encontrado")
        void deveBuscarCorteTecidoPorId_QuandoEncontrado() {
            // Arrange
            CorteTecido expectedCorteTecido = createValidCorteTecido();
            setupSuccessfulFindById(expectedCorteTecido);

            // Act
            CorteTecido resultado = corteTecidoService.buscarCorteTecidoPorId(VALID_ID);

            // Assert
            assertCorteTecidoFoundSuccessfully(resultado, expectedCorteTecido);
            verifyFindByIdInteractions(VALID_ID);
        }

        @Test
        @DisplayName("Deve lançar exceção quando buscar corte tecido por ID inexistente")
        void deveLancarExcecao_QuandoBuscarCorteTecidoPorIdInexistente() {
            // Arrange
            setupCorteTecidoNotFound();

            // Act & Assert
            assertThrows(CorteTecidoNaoEncontradoException.class,
                    () -> corteTecidoService.buscarCorteTecidoPorId(INVALID_ID));

            verifyFindByIdInteractions(INVALID_ID);
        }

        private void setupSuccessfulListRetrieval(List<CorteTecido> expectedList) {
            when(corteTecidoRepository.findAll()).thenReturn(expectedList);
        }

        private void setupSuccessfulFindById(CorteTecido corteTecido) {
            when(corteTecidoRepository.findById(VALID_ID)).thenReturn(Optional.of(corteTecido));
        }

        private void setupCorteTecidoNotFound() {
            when(corteTecidoRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
        }

        private void assertListReturnedSuccessfully(List<CorteTecido> resultado, List<CorteTecido> expected) {
            assertFalse(resultado.isEmpty());
            assertEquals(expected.size(), resultado.size());
            assertEquals(expected.get(0).getIdCorteTecido(), resultado.get(0).getIdCorteTecido());
        }

        private void assertCorteTecidoFoundSuccessfully(CorteTecido resultado, CorteTecido expected) {
            assertNotNull(resultado);
            assertEquals(expected.getIdCorteTecido(), resultado.getIdCorteTecido());
            assertEquals(expected.getInicio(), resultado.getInicio());
        }

        private void verifyListRetrievalInteractions() {
            verify(corteTecidoRepository, times(1)).findAll();
        }

        private void verifyFindByIdInteractions(Integer id) {
            verify(corteTecidoRepository, times(1)).findById(id);
        }
    }

    @Nested
    @DisplayName("Atualização de Corte Tecido")
    class AtualizacaoCorteTecidoTests {

        @Test
        @DisplayName("Deve atualizar corte tecido com sucesso quando existe")
        void deveAtualizarCorteTecido_QuandoExiste() {
            // Arrange
            CorteTecido corteTecidoAtualizado = createValidCorteTecido();
            setupSuccessfulUpdate(corteTecidoAtualizado);

            // Act
            CorteTecido resultado = corteTecidoService.atualizarCorteTecido(VALID_ID, corteTecidoAtualizado);

            // Assert
            assertCorteTecidoUpdatedSuccessfully(resultado);
            verifyUpdateInteractions(corteTecidoAtualizado);
        }

        @Test
        @DisplayName("Deve lançar exceção quando tentar atualizar corte tecido inexistente")
        void deveLancarExcecao_QuandoAtualizarCorteTecidoInexistente() {
            // Arrange
            CorteTecido corteTecido = createValidCorteTecido();
            setupCorteTecidoNotFoundForUpdate();

            // Act & Assert
            assertThrows(CorteTecidoNaoEncontradoException.class,
                    () -> corteTecidoService.atualizarCorteTecido(INVALID_ID, corteTecido));

            verifyExistsInteractions(INVALID_ID);
            verifyNoSaveInteraction();
        }

        private void setupSuccessfulUpdate(CorteTecido corteTecido) {
            when(corteTecidoRepository.existsById(VALID_ID)).thenReturn(true);
            when(corteTecidoRepository.save(any(CorteTecido.class))).thenReturn(corteTecido);
        }

        private void setupCorteTecidoNotFoundForUpdate() {
            when(corteTecidoRepository.existsById(INVALID_ID)).thenReturn(false);
        }

        private void assertCorteTecidoUpdatedSuccessfully(CorteTecido resultado) {
            assertNotNull(resultado);
            assertEquals(VALID_ID, resultado.getIdCorteTecido());
            assertEquals(VALID_INICIO, resultado.getInicio());
        }

        private void verifyUpdateInteractions(CorteTecido corteTecido) {
            verify(corteTecidoRepository, times(1)).existsById(VALID_ID);
            verify(corteTecidoRepository, times(1)).save(corteTecido);
        }

        private void verifyExistsInteractions(Integer id) {
            verify(corteTecidoRepository, times(1)).existsById(id);
        }

        private void verifyNoSaveInteraction() {
            verify(corteTecidoRepository, never()).save(any(CorteTecido.class));
        }
    }

    @Nested
    @DisplayName("Remoção de Corte Tecido")
    class RemocaoCorteTecidoTests {

        @Test
        @DisplayName("Deve deletar corte tecido com sucesso quando existe")
        void deveDeletarCorteTecido_QuandoExiste() {
            // Arrange
            setupSuccessfulDeletion();

            // Act
            corteTecidoService.deletarCorteTecido(VALID_ID);

            // Assert
            verifyDeletionInteractions();
        }

        @Test
        @DisplayName("Deve lançar exceção quando tentar deletar corte tecido inexistente")
        void deveLancarExcecao_QuandoDeletarCorteTecidoInexistente() {
            // Arrange
            setupCorteTecidoNotFoundForDeletion();

            // Act & Assert
            assertThrows(CorteTecidoNaoEncontradoException.class,
                    () -> corteTecidoService.deletarCorteTecido(INVALID_ID));

            verifyExistsInteractions(INVALID_ID);
            verifyNoDeleteInteraction();
        }

        private void setupSuccessfulDeletion() {
            when(corteTecidoRepository.existsById(VALID_ID)).thenReturn(true);
            doNothing().when(corteTecidoRepository).deleteById(VALID_ID);
        }

        private void setupCorteTecidoNotFoundForDeletion() {
            when(corteTecidoRepository.existsById(INVALID_ID)).thenReturn(false);
        }

        private void verifyDeletionInteractions() {
            verify(corteTecidoRepository, times(1)).existsById(VALID_ID);
            verify(corteTecidoRepository, times(1)).deleteById(VALID_ID);
        }

        private void verifyExistsInteractions(Integer id) {
            verify(corteTecidoRepository, times(1)).existsById(id);
        }

        private void verifyNoDeleteInteraction() {
            verify(corteTecidoRepository, never()).deleteById(any());
        }
    }

    // Factory methods for test data creation
    private CorteTecido createValidCorteTecido() {
        return CorteTecido.builder()
                .idCorteTecido(VALID_ID)
                .inicio(VALID_INICIO)
                .termino(VALID_TERMINO)
                .funcionario(createValidFuncionario())
                .loteItemEstoque(createValidLoteItemEstoque())
                .build();
    }

    private CorteTecido createValidCorteTecidoWithoutId() {
        return CorteTecido.builder()
                .inicio(VALID_INICIO)
                .termino(VALID_TERMINO)
                .funcionario(createValidFuncionario())
                .loteItemEstoque(createValidLoteItemEstoque())
                .build();
    }

    private Funcionario createValidFuncionario() {
        return Funcionario.builder()
                .idFuncionario(FUNCIONARIO_ID)
                .nome(FUNCIONARIO_NOME)
                .build();
    }

    private LoteItemEstoque createValidLoteItemEstoque() {
        return LoteItemEstoque.builder()
                .idLoteItemEstoque(LOTE_ID)
                .build();
    }

    private List<CorteTecido> createCorteTecidoList() {
        return Arrays.asList(createValidCorteTecido());
    }

    // Alternative factory method if builder pattern is not available
    private CorteTecido createValidCorteTecidoLegacy() {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(FUNCIONARIO_ID);
        funcionario.setNome(FUNCIONARIO_NOME);

        LoteItemEstoque lote = new LoteItemEstoque();
        lote.setIdLoteItemEstoque(LOTE_ID);

        CorteTecido corte = new CorteTecido();
        corte.setIdCorteTecido(VALID_ID);
        corte.setInicio(VALID_INICIO);
        corte.setTermino(VALID_TERMINO);
        corte.setFuncionario(funcionario);
        corte.setLoteItemEstoque(lote);

        return corte;
    }
}