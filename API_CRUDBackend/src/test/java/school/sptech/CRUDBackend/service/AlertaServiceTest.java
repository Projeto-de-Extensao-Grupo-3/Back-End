package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.CRUDBackend.entity.*;
import school.sptech.CRUDBackend.exception.Lote.LoteConflitoException;
import school.sptech.CRUDBackend.repository.AlertaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertaServiceTest {

    @Mock
    private AlertaRepository alertaRepository;

    @InjectMocks
    private AlertaService alertaService;

    private Alerta alerta;

    private ItemEstoque itemEstoque;

    private Categoria categoriaFilho;

    private Categoria categoriaPai;

    private Set<Categoria> caracteristicas = new HashSet<>();

    private Prateleira prateleira;

    private Set<ConfeccaoRoupa> confeccaoRoupas = new HashSet<>();

    @BeforeEach
    void setUp(){
        Categoria tecido = new Categoria(1, "Tecido", null);
        categoriaPai = new Categoria(2, "Vestido", null);
        categoriaFilho = new Categoria(3, "Vestido Florido", categoriaPai);
        Categoria caracteristica1 = new Categoria(4, "Jeans", tecido);
        Categoria caracteristica2 = new Categoria(5, "Algodão", tecido);
        caracteristicas.add(caracteristica1);
        caracteristicas.add(caracteristica2);
        prateleira = new Prateleira(1, "1A");
        itemEstoque = new ItemEstoque(1, "Vestido com Flores Azuis", "Tamanho Unico", 1.5,
                10.0, 50.0, categoriaFilho, caracteristicas, prateleira, confeccaoRoupas);
        alerta = new Alerta(1, "Quantidade minima de roupa atingida",
                LocalDateTime.of(2025, 5, 2, 10, 1, 30), itemEstoque);
    }

    @Test
    @DisplayName("Deve cadastrar novo alarme com sucesso")
    void deveCadastrarNovoAlarmeComSucesso(){
        // Given

        // When
        when(alertaRepository.save(any())).thenReturn(alerta);

        //Then
        Alerta resultado = alertaService.criarAlerta(alerta);

        //Assert
        verify(alertaRepository, times(1)).save(any());
        assertEquals(alerta, resultado);
    }

    @Test
    @DisplayName("Deve retornar lista com 1 Alerta do respectivo Item")
    void deveListarAlertaDoItem(){
        //Given
        List<Alerta> alertas = List.of(alerta);

        //When
        when(alertaRepository.findByItemEstoque(itemEstoque)).thenReturn(alertas);

        //Then
        List<Alerta> resultado = alertaService.listarAlertasDoItem(itemEstoque);

        //Assert
        assertEquals(alertas, resultado);
    }

}