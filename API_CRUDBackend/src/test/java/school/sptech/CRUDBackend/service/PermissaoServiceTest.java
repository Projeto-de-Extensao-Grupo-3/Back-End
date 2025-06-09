package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.*;
import school.sptech.CRUDBackend.entity.Permissao;
import school.sptech.CRUDBackend.repository.PermissaoRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Permissão Service Tests")
class PermissaoServiceTest {

    // Constants for test data
    private static final Integer VALID_ID_1 = 1;
    private static final Integer VALID_ID_2 = 2;
    private static final String VALID_DESCRIPTION_1 = "Acesso a dashboard";
    private static final String VALID_DESCRIPTION_2 = "Acesso a relatórios";
    private static final int EXPECTED_LIST_SIZE = 2;
    private static final int EMPTY_LIST_SIZE = 0;

    @InjectMocks
    private PermissaoService permissaoService;

    @Mock
    private PermissaoRepository permissaoRepository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }

    @Nested
    @DisplayName("Consulta de Permissões")
    class ConsultaPermissoesTests {

        @Test
        @DisplayName("Deve retornar lista quando existem permissões")
        void deveRetornarLista_QuandoExistemPermissoes() {
            // Arrange
            List<Permissao> expectedPermissoes = createPermissoesList();
            setupSuccessfulPermissoesRetrieval(expectedPermissoes);

            // Act
            List<Permissao> resultado = permissaoService.verificarTodasPermissoes();

            // Assert
            assertPermissoesReturnedSuccessfully(resultado, expectedPermissoes);
            verifyPermissoesRetrievalInteractions();
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando não houver permissões")
        void deveRetornarListaVazia_QuandoNaoHouverPermissoes() {
            // Arrange
            List<Permissao> emptyList = createEmptyPermissoesList();
            setupEmptyPermissoesRetrieval(emptyList);

            // Act
            List<Permissao> resultado = permissaoService.verificarTodasPermissoes();

            // Assert
            assertEmptyListReturnedSuccessfully(resultado);
            verifyPermissoesRetrievalInteractions();
        }

        private void setupSuccessfulPermissoesRetrieval(List<Permissao> permissoes) {
            when(permissaoRepository.findAll()).thenReturn(permissoes);
        }

        private void setupEmptyPermissoesRetrieval(List<Permissao> emptyList) {
            when(permissaoRepository.findAll()).thenReturn(emptyList);
        }

        private void assertPermissoesReturnedSuccessfully(List<Permissao> resultado, List<Permissao> expected) {
            assertNotNull(resultado);
            assertEquals(EXPECTED_LIST_SIZE, resultado.size());
            assertEquals(expected.get(0).getDescricao(), resultado.get(0).getDescricao());
            assertEquals(expected.get(1).getDescricao(), resultado.get(1).getDescricao());
        }

        private void assertEmptyListReturnedSuccessfully(List<Permissao> resultado) {
            assertNotNull(resultado);
            assertEquals(EMPTY_LIST_SIZE, resultado.size());
            assertTrue(resultado.isEmpty());
        }

        private void verifyPermissoesRetrievalInteractions() {
            verify(permissaoRepository, times(1)).findAll();
        }
    }

    // Factory methods for test data creation
    private List<Permissao> createPermissoesList() {
        return Arrays.asList(
                createPermissao(VALID_ID_1, VALID_DESCRIPTION_1),
                createPermissao(VALID_ID_2, VALID_DESCRIPTION_2)
        );
    }

    private List<Permissao> createEmptyPermissoesList() {
        return Collections.emptyList();
    }

    private Permissao createPermissao(Integer id, String descricao) {
        return new Permissao(id, descricao);
    }
}