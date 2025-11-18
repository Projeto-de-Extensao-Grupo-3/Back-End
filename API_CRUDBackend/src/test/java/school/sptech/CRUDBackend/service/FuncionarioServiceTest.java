package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.CRUDBackend.config.GerenciadorTokenJwt;
import school.sptech.CRUDBackend.dto.dtoLogin.FuncionarioTokenDto;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.exception.funcionario.FuncionarioConflitoException;
import school.sptech.CRUDBackend.exception.funcionario.FuncionarioNaoEncontradoException;
import school.sptech.CRUDBackend.repository.FuncionarioRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Funcionário Service Tests")
class FuncionarioServiceTest {

    // Constants for test data
    private static final String VALID_NAME = "Ana Silva";
    private static final String VALID_CPF = "12345678900";
    private static final String VALID_PHONE = "11999999999";
    private static final String VALID_EMAIL = "ana@email.com";
    private static final String VALID_PASSWORD = "senha123";
    private static final String ENCRYPTED_PASSWORD = "senhaCriptografada";
    private static final String JWT_TOKEN = "token-jwt";
    private static final Integer VALID_ID = 1;
    private static final Integer INVALID_ID = 999;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;

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
    @DisplayName("Cadastro de Funcionário")
    class CadastroFuncionarioTests {

        @Test
        @DisplayName("Deve cadastrar funcionário com sucesso quando dados são válidos")
        void deveCadastrarFuncionarioComSucesso_QuandoDadosValidos() {
            // Arrange
            Funcionario funcionario = createValidFuncionario();
            setupSuccessfulRegistration();

            // Act
            Funcionario resultado = funcionarioService.cadastrarFuncionario(funcionario);

            // Assert
            assertFuncionarioRegisteredSuccessfully(resultado);
            verifyRegistrationInteractions(funcionario);
        }

        @Test
        @DisplayName("Deve lançar exceção quando funcionário já existe")
        void deveLancarExcecao_QuandoFuncionarioExistente() {
            // Arrange
            Funcionario funcionario = createValidFuncionario();
            setupExistingFuncionario();

            // Act & Assert
            assertThrows(FuncionarioConflitoException.class,
                    () -> funcionarioService.cadastrarFuncionario(funcionario));

            verifyNoSaveInteraction();
        }

        private void setupSuccessfulRegistration() {
            when(funcionarioRepository.existsByCpfOrEmail(VALID_CPF, VALID_EMAIL))
                    .thenReturn(false);
            when(passwordEncoder.encode(VALID_PASSWORD))
                    .thenReturn(ENCRYPTED_PASSWORD);
            when(funcionarioRepository.save(any()))
                    .thenAnswer(invocation -> invocation.getArgument(0));
        }

        private void setupExistingFuncionario() {
            when(funcionarioRepository.existsByCpfOrEmail(VALID_CPF, VALID_EMAIL))
                    .thenReturn(true);
        }

        private void assertFuncionarioRegisteredSuccessfully(Funcionario resultado) {
            assertNotNull(resultado);
            assertEquals(ENCRYPTED_PASSWORD, resultado.getSenha());
        }

        private void verifyRegistrationInteractions(Funcionario funcionario) {
            verify(funcionarioRepository).existsByCpfOrEmail(VALID_CPF, VALID_EMAIL);
            verify(passwordEncoder).encode(VALID_PASSWORD);
            verify(funcionarioRepository).save(funcionario);
        }

        private void verifyNoSaveInteraction() {
            verify(funcionarioRepository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("Autenticação de Funcionário")
    class AutenticacaoFuncionarioTests {

        @Test
        @DisplayName("Deve retornar token quando credenciais são válidas")
        void deveRetornarToken_QuandoCredenciaisValidas() {
            // Arrange
            Funcionario funcionario = createValidFuncionario();
            setupSuccessfulAuthentication(funcionario);

            // Act
            FuncionarioTokenDto tokenDto = funcionarioService.autenticar(funcionario);

            // Assert
            assertTokenGeneratedSuccessfully(tokenDto, funcionario);
            verifyAuthenticationInteractions(funcionario);
        }

        private void setupSuccessfulAuthentication(Funcionario funcionario) {
            when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                    .thenReturn(authentication);
            when(funcionarioRepository.findByEmail(funcionario.getEmail()))
                    .thenReturn(Optional.of(funcionario));
            when(gerenciadorTokenJwt.generateToken(authentication))
                    .thenReturn(JWT_TOKEN);
        }

        private void assertTokenGeneratedSuccessfully(FuncionarioTokenDto tokenDto, Funcionario funcionario) {
            assertNotNull(tokenDto);
            assertEquals(JWT_TOKEN, tokenDto.getToken());
            assertEquals(funcionario.getEmail(), tokenDto.getEmail());
        }

        private void verifyAuthenticationInteractions(Funcionario funcionario) {
            verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
            verify(funcionarioRepository).findByEmail(funcionario.getEmail());
            verify(gerenciadorTokenJwt).generateToken(authentication);
        }
    }

    @Nested
    @DisplayName("Consulta de Funcionários")
    class ConsultaFuncionariosTests {

        @Test
        @DisplayName("Deve retornar lista quando existem funcionários")
        void deveRetornarLista_QuandoExistemFuncionarios() {
            // Arrange
            List<Funcionario> expectedList = createFuncionarioList();
            when(funcionarioRepository.findAll()).thenReturn(expectedList);

            // Act
            List<Funcionario> resultado = funcionarioService.verificarTodosFuncionarios();

            // Assert
            assertListReturnedSuccessfully(resultado, expectedList);
        }

//        @Test
//        @DisplayName("Deve retornar funcionário quando encontrado por ID")
//        void deveRetornarFuncionario_QuandoEncontradoPorId() {
//            // Arrange
//            Funcionario expectedFuncionario = createFuncionarioWithId(VALID_ID);
//            when(funcionarioRepository.findById(VALID_ID))
//                    .thenReturn(Optional.of(expectedFuncionario));
//
//            // Act
//            Funcionario resultado = funcionarioService.buscarFuncionarioPorId(VALID_ID);
//
//            // Assert
//            assertEquals(expectedFuncionario.getNome(), resultado.getNome());
//            assertEquals(expectedFuncionario.getIdFuncionario(), resultado.getIdFuncionario());
//        }

//        @Test
//        @DisplayName("Deve lançar exceção quando funcionário não é encontrado por ID")
//        void deveLancarExcecao_QuandoFuncionarioNaoEncontradoPorId() {
//            // Arrange
//            when(funcionarioRepository.findById(INVALID_ID))
//                    .thenReturn(Optional.empty());
//
//            // Act & Assert
//            assertThrows(FuncionarioNaoEncontradoException.class,
//                    () -> funcionarioService.buscarFuncionarioPorId(INVALID_ID));
//        }

        private void assertListReturnedSuccessfully(List<Funcionario> resultado, List<Funcionario> expected) {
            assertEquals(expected.size(), resultado.size());
            assertEquals(expected.get(0).getNome(), resultado.get(0).getNome());
        }
    }

    @Nested
    @DisplayName("Atualização de Funcionário")
    class AtualizacaoFuncionarioTests {

        @Test
        @DisplayName("Deve atualizar funcionário quando existe")
        void deveAtualizarFuncionario_QuandoExiste() {
            // Arrange
            Funcionario funcionarioAtualizado = createUpdatedFuncionario();
            setupExistingFuncionarioForUpdate();

            // Act
            Funcionario resultado = funcionarioService.atualizarFuncionarioPorId(VALID_ID, funcionarioAtualizado);

            // Assert
            assertFuncionarioUpdatedSuccessfully(resultado, funcionarioAtualizado);
            verifyUpdateInteractions();
        }

        @Test
        @DisplayName("Deve lançar exceção quando funcionário não existe para atualização")
        void deveLancarExcecao_QuandoFuncionarioNaoExisteParaAtualizacao() {
            // Arrange
            Funcionario funcionario = createValidFuncionario();
            when(funcionarioRepository.existsById(INVALID_ID)).thenReturn(false);

            // Act & Assert
            assertThrows(FuncionarioNaoEncontradoException.class,
                    () -> funcionarioService.atualizarFuncionarioPorId(INVALID_ID, funcionario));
        }

        private void setupExistingFuncionarioForUpdate() {
            when(funcionarioRepository.existsById(VALID_ID)).thenReturn(true);
            when(funcionarioRepository.save(any()))
                    .thenAnswer(invocation -> invocation.getArgument(0));
        }

        private void assertFuncionarioUpdatedSuccessfully(Funcionario resultado, Funcionario expected) {
            assertEquals(VALID_ID, resultado.getIdFuncionario());
            assertEquals(expected.getNome(), resultado.getNome());
        }

        private void verifyUpdateInteractions() {
            verify(funcionarioRepository).existsById(VALID_ID);
            verify(funcionarioRepository).save(any());
        }
    }

    @Nested
    @DisplayName("Remoção de Funcionário")
    class RemocaoFuncionarioTests {

        @Test
        @DisplayName("Deve remover funcionário quando existe")
        void deveRemoverFuncionario_QuandoExiste() {
            // Arrange
            when(funcionarioRepository.existsById(VALID_ID)).thenReturn(true);

            // Act
            funcionarioService.removerFuncionarioPorId(VALID_ID);

            // Assert
            verify(funcionarioRepository).existsById(VALID_ID);
            verify(funcionarioRepository).deleteById(VALID_ID);
        }

        @Test
        @DisplayName("Deve lançar exceção quando funcionário não existe para remoção")
        void deveLancarExcecao_QuandoFuncionarioNaoExisteParaRemocao() {
            // Arrange
            when(funcionarioRepository.existsById(INVALID_ID)).thenReturn(false);

            // Act & Assert
            assertThrows(FuncionarioNaoEncontradoException.class,
                    () -> funcionarioService.removerFuncionarioPorId(INVALID_ID));

            verify(funcionarioRepository, never()).deleteById(INVALID_ID);
        }
    }

    // Factory methods for test data creation
    private Funcionario createValidFuncionario() {
        return new Funcionario(null, VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, Set.of());
    }

    private Funcionario createFuncionarioWithId(Integer id) {
        return new Funcionario(id, VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, Set.of());
    }

    private Funcionario createUpdatedFuncionario() {
        return new Funcionario(null, "Ana Silva Atualizada", VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, Set.of());
    }

    private List<Funcionario> createFuncionarioList() {
        return List.of(createFuncionarioWithId(VALID_ID));
    }
}