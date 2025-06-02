package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.Lote;
import school.sptech.CRUDBackend.entity.Parceiro;
import school.sptech.CRUDBackend.entity.Permissao;
import school.sptech.CRUDBackend.exception.Lote.LoteConflitoException;
import school.sptech.CRUDBackend.exception.Lote.LoteNaoEncontradoException;
import school.sptech.CRUDBackend.exception.parceiro.ParceiroConflitoException;
import school.sptech.CRUDBackend.exception.parceiro.ParceiroNaoEncontradoException;
import school.sptech.CRUDBackend.repository.LoteRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoteServiceTest {

    @Mock
    private LoteRepository loteRepository;

    @InjectMocks
    private LoteService loteService;

    private Lote loteTeste;

    private Parceiro parceiroTeste;

    private Funcionario funcionarioTeste;

    private Set<Permissao> listaPermissaoTeste = new HashSet<>();

    private Permissao permissaoTeste1;

    private Permissao permissaoTeste2;

    @BeforeEach
    void setUp(){
        parceiroTeste = new Parceiro(1, "Fabricante", "Fornecedor de Algodão", "000", "fabricante@gmail.com",
                "R. Haddock Lobo, 595", "teste1");
        permissaoTeste1 = new Permissao(1, "Cadastrar Funcionario");
        listaPermissaoTeste.add(permissaoTeste1);
        permissaoTeste2 = new Permissao(2, "Visualizar Dashboard");
        listaPermissaoTeste.add(permissaoTeste2);
        funcionarioTeste = new Funcionario(1, "Leandro", "123456789", "11 977839256",
                "leandro@gmail.com", "123", listaPermissaoTeste);
        loteTeste = new Lote(1, "Lote de Tecido Algodão", "10/06/2025", parceiroTeste, funcionarioTeste);
    }

    @Test
    @DisplayName("Quando enviado uma classe de Lote completa, deve ser cadastrada com sucesso")
    void deveCadastrarNovoLoteComSucesso(){
        // Given

        // When
        when(loteRepository.existsByDescricao(anyString())).thenReturn(false);
        when(loteRepository.save(any())).thenReturn(loteTeste);

        //Then
        Lote resultado = loteService.cadastrarLote(loteTeste);

        //Assert
        verify(loteRepository, times(1)).save(any());
        assertEquals(loteTeste, resultado);
    }

    @Test
    @DisplayName("Quando acionado deve lançar excessão para descricao de lote repetida")
    void deveLancarExcessaoDeConflitoParaCadastroDeLote(){
        // Given

        // When
        when(loteRepository.existsByDescricao(anyString())).thenReturn(true);

        //Then

        //Assert
        assertThrows(LoteConflitoException.class, () -> loteService.cadastrarLote(loteTeste));
    }

    @Test
    @DisplayName("Deve retornar lista com 1 lote cadastrado")
    void deveListar2ParceirosCadastrados(){
        //Given
        List<Lote> lotes = List.of(loteTeste);

        //When
        when(loteRepository.findAll()).thenReturn(lotes);

        //Then
        List<Lote> resultado = loteService.listarTodosOsLotes();

        //Assert
        assertEquals(lotes, resultado);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia sem Lotes no sistema")
    void deveRetornarListaDeLotesVazia(){
        //Given

        //When
        when(loteRepository.findAll()).thenReturn(Collections.emptyList());

        //Then
        List<Lote> resultado = loteService.listarTodosOsLotes();

        //Assert
        assertTrue(resultado.isEmpty(), "A lista deve estar vazia");
        assertEquals(0, resultado.size());
    }


    @Test
    @DisplayName("Deve retornar Lote com ID informado")
    void deveRetornarLoteComIdInformado(){
        //Given

        //When
        when(loteRepository.findById(anyInt())).thenReturn(Optional.of(loteTeste));

        //Then
        Lote resultado = loteService.buscarLotePorId(loteTeste.getIdLote());

        //Assert
        assertEquals(loteTeste, resultado);
        assertEquals(loteTeste.getResponsavel(), resultado.getResponsavel());
    }

    @Test
    @DisplayName("Deve lanca excessao quando ID de Lote for invalido")
    void deveLancarExcessaoParaIdLoteInvalido(){
        //Given

        //When
        when(loteRepository.findById(anyInt())).thenReturn(Optional.empty());

        //Then

        //Assert
        assertThrows(LoteNaoEncontradoException.class, () -> loteService.buscarLotePorId(loteTeste.getIdLote()));
    }

    @Test
    @DisplayName("Deve atualizar Lote com novos dados e ID informado")
    void deveAtualizarLoteComNovosDados(){
        //Given
        Parceiro parceiroParaAtualizar = new Parceiro(2, "Fabricante", "Fornecedor de Algodões Peruanos", "000", "fabricante2@gmail.com",
                "R. Haddock Lobo, 595", "teste1");
        Lote loteParaAtualizar = new Lote(1, "Lote de Algodão Peruano", "10/06/2025", parceiroParaAtualizar, funcionarioTeste);

        //When
        when(loteRepository.existsById(anyInt())).thenReturn(true);
        when(loteRepository.save(loteParaAtualizar)).thenReturn(loteParaAtualizar);

        //Then
        Lote resultado = loteService.atualizarLotePorId(loteTeste.getIdLote(), loteParaAtualizar);

        //Assert
        verify(parceiroRepository, times(1)).save(any());
        assertEquals(parceiroParaAtualizar, resultado);
        assertEquals(parceiroParaAtualizar.getTelefone(), resultado.getTelefone());
        assertEquals(parceiroParaAtualizar.getEmail(), resultado.getEmail());
        assertEquals(parceiroParaAtualizar.getEndereco(), resultado.getEndereco());
    }


}