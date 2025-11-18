package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.CRUDBackend.entity.Parceiroaa;
import school.sptech.CleanArchitecture.core.application.exceptions.parceiro.ParceiroConflitoException;
import school.sptech.CleanArchitecture.core.application.exceptions.parceiro.ParceiroNaoEncontradoException;
import school.sptech.CRUDBackend.repository.ParceiroRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParceiroServiceTest {

    @Mock
    private ParceiroRepository parceiroRepository;

    @InjectMocks
    private ParceiroService parceiroService;

    private Parceiroaa parceiroFabricante;

    private Parceiroaa parceiroCostureira;

    @BeforeEach
    void setUp(){
        parceiroFabricante = new Parceiroaa(1, "Fabricante", "Fornecedor de Jeans", "000", "fabricante@gmail.com", "R. Haddock Lobo, 595", "teste1");
        parceiroCostureira = new Parceiroaa(2, "Costureira", "Dona Neuza", "000", "neuza@gmail.com", "R. Haddock Lobo, 585", "teste2");
    }

    @Test
    @DisplayName("Quando enviado uma classe de Parceiro completo, deve ser cadastrado com sucesso")
    void deveCadastrarNovoParceiroComSucesso(){
        // Given

        // When
        when(parceiroRepository.existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(anyString(), anyString(), anyString())).thenReturn(false);
        when(parceiroRepository.save(any())).thenReturn(parceiroFabricante);

        //Then
        Parceiroaa resultado = parceiroService.cadastrarParceiro(parceiroFabricante);

        //Assert
        verify(parceiroRepository, times(1)).save(any());
        assertEquals(parceiroFabricante, resultado);
    }

    @Test
    @DisplayName("Quando acionado deve lançar excessão para dados de parceiro repetido")
    void deveLancarExcessaoDeConflitoParaCadastroDeParceiro(){
        // Given

        // When
        when(parceiroRepository.existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(anyString(), anyString(), anyString())).thenReturn(true);

        //Then

        //Assert
        assertThrows(ParceiroConflitoException.class, () -> parceiroService.cadastrarParceiro(parceiroFabricante));
    }

    @Test
    @DisplayName("Deve costureira cadastrada no sistema")
    void deveListar2ParceirosCadastrados(){
        //Given
        List<Parceiroaa> parceiros = List.of(parceiroCostureira);

        //When
        when(parceiroRepository.findAll()).thenReturn(parceiros);

        //Then
        List<Parceiroaa> resultado = parceiroService.verificarTodosParceiros("costureira");

        //Assert
        assertEquals(parceiros, resultado);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia sem parceiros no sistema")
    void deveRetornarListaVazia(){
        //Given

        //When
        when(parceiroRepository.findAll()).thenReturn(Collections.emptyList());

        //Then
        List<Parceiroaa> resultado = parceiroService.verificarTodosParceiros("fornecedor");

        //Assert
        assertTrue(resultado.isEmpty(), "A lista deve estar vazia");
        assertEquals(0, resultado.size());
    }

//    @Test
//    @DisplayName("Deve retornar parceiro com ID informado")
//    void deveRetornarParceiroComIdInformado(){
//        //Given
//
//        //When
//        when(parceiroRepository.findById(anyInt())).thenReturn(Optional.of(parceiroCostureira));
//
//        //Then
//        Parceiro resultado = parceiroService.buscarParceiroPorId(parceiroCostureira.getIdParceiro());
//
//        //Assert
//        assertEquals(parceiroCostureira, resultado);
//        assertEquals(parceiroCostureira.getCategoria(), resultado.getCategoria());
//    }

//    @Test
//    @DisplayName("Deve lanca excessao quando ID for invalido")
//    void deveLancarExcessaoParaIdInvalido(){
//        //Given
//
//        //When
//        when(parceiroRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        //Then
//
//        //Assert
//        assertThrows(ParceiroNaoEncontradoException.class, () -> parceiroService.buscarParceiroPorId(parceiroCostureira.getIdParceiro()));
//    }

    @Test
    @DisplayName("Deve atualizar parceiro com novos dados e ID informado")
    void deveAtualizarParceiroComNovosDados(){
        //Given
        Parceiroaa parceiroParaAtualizar = new Parceiroaa(null, "Fabricante", "Fornecedor de Jeans", "5050", "novoemailfab@gmail.com", "R. Lobo de Wall Street, 595", "teste1");

        //When
        when(parceiroRepository.existsById(anyInt())).thenReturn(true);
        when(parceiroRepository.existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(anyString(), anyString(), anyString())).thenReturn(false);
        when(parceiroRepository.save(parceiroParaAtualizar)).thenReturn(parceiroParaAtualizar);

        //Then
        Parceiroaa resultado = parceiroService.atualizarParceiroPorId(parceiroFabricante.getIdParceiro(), parceiroParaAtualizar);

        //Assert
        verify(parceiroRepository, times(1)).save(any());
        assertEquals(parceiroParaAtualizar, resultado);
        assertEquals(parceiroParaAtualizar.getTelefone(), resultado.getTelefone());
        assertEquals(parceiroParaAtualizar.getEmail(), resultado.getEmail());
        assertEquals(parceiroParaAtualizar.getEndereco(), resultado.getEndereco());
    }

    @Test
    @DisplayName("Deve lancar excessao ao tentar atualizar ID de parceiro invalido")
    void deveLancarExcessaoParaIdNaoEncontrado(){
        //Given
        Parceiroaa parceiroParaAtualizar = new Parceiroaa(null, "Fabricante", "Fornecedor de Jeans", "5050", "novoemailfab@gmail.com", "R. Lobo de Wall Street, 595", "teste1");

        //When
        when(parceiroRepository.existsById(anyInt())).thenReturn(false);

        //Then

        //Assert
        assertThrows(ParceiroNaoEncontradoException.class, () -> parceiroService.atualizarParceiroPorId(anyInt(), parceiroParaAtualizar));
    }

    @Test
    @DisplayName("Deve remover parceiro com ID informado")
    void deveRemoverParceiroComIdInformado(){
        //Given

        //When
        when(parceiroRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(parceiroRepository).deleteById(anyInt());

        //Then
        parceiroService.removerParceiroPorId(parceiroCostureira.getIdParceiro());

        //Assert
        verify(parceiroRepository, times(1)).deleteById(parceiroCostureira.getIdParceiro());
    }

    @Test
    @DisplayName("Deve lancar excessao ao tentar remover parceiro com ID invalido")
    void deveLancarExcessaoParaIdInvalidoAoTentarRemover(){
        //Given

        //When
        when(parceiroRepository.existsById(anyInt())).thenReturn(false);

        //Then

        //Assert
        assertThrows(ParceiroNaoEncontradoException.class, () -> parceiroService.removerParceiroPorId(parceiroCostureira.getIdParceiro()));
        verify(parceiroRepository, never()).delete(any());
    }

}