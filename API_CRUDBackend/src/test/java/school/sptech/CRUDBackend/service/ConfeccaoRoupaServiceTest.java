package school.sptech.CRUDBackend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.CRUDBackend.entity.ConfeccaoRoupa;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.exception.Categoria.CategoriaConflitoException;
import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CRUDBackend.exception.confeccaoRoupa.ConfeccaoRoupaNaoEncontradaException;
import school.sptech.CRUDBackend.repository.ConfeccaoRoupaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfeccaoRoupaServiceTest {

    @Mock
    private ConfeccaoRoupa entity;

    @Mock
    private ConfeccaoRoupaRepository repository;

    @InjectMocks
    private ConfeccaoRoupaService service;

    @Test
    void deveCadastrarConfeccaoRoupaComSucessoQuandoDadosValidos() {
        ItemEstoque tecidoEstoque = new ItemEstoque();
        tecidoEstoque.setIdItemEstoque(1);
        tecidoEstoque.setDescricao("descricao tecido");
        tecidoEstoque.setComplemento("complemento tecido");
        tecidoEstoque.setPeso(2.5);
        tecidoEstoque.setQtdMinimo(1.0);
        tecidoEstoque.setQtdArmazenado(10.0);

        ItemEstoque roupaEstoque = new ItemEstoque();
        roupaEstoque.setIdItemEstoque(2);
        roupaEstoque.setDescricao("descricao tecido");
        roupaEstoque.setComplemento("complemento tecido");
        roupaEstoque.setPeso(2.5);
        roupaEstoque.setQtdMinimo(1.0);
        roupaEstoque.setQtdArmazenado(10.0);

        ConfeccaoRoupa confeccaoRoupa = new ConfeccaoRoupa(
                1,
                roupaEstoque,
                tecidoEstoque,
                1.0
        );

        when(repository.save(confeccaoRoupa)).thenReturn(confeccaoRoupa);

        ConfeccaoRoupa resultado = service.cadastrarconfeccaoRoupa(confeccaoRoupa);

        assertNotNull(resultado);
        assertEquals(1.0, resultado.getQtdTecido());
        assertEquals(tecidoEstoque, resultado.getTecido());
        assertEquals(roupaEstoque, resultado.getRoupa());
    }

    @Test
    void deveFalharAoCadastrarConfeccaoRoupaComIdInvalido() {
        ItemEstoque tecidoEstoque = new ItemEstoque();
        tecidoEstoque.setIdItemEstoque(1);
        tecidoEstoque.setDescricao("descricao tecido");
        tecidoEstoque.setComplemento("complemento tecido");
        tecidoEstoque.setPeso(2.5);
        tecidoEstoque.setQtdMinimo(1.0);
        tecidoEstoque.setQtdArmazenado(10.0);

        ItemEstoque roupaEstoque = new ItemEstoque();
        roupaEstoque.setIdItemEstoque(2);
        roupaEstoque.setDescricao("descricao tecido");
        roupaEstoque.setComplemento("complemento tecido");
        roupaEstoque.setPeso(2.5);
        roupaEstoque.setQtdMinimo(1.0);
        roupaEstoque.setQtdArmazenado(10.0);

        ConfeccaoRoupa confeccaoRoupa = new ConfeccaoRoupa(
                1,
                roupaEstoque,
                tecidoEstoque,
                1.0
        );

        when(repository.existsByRoupaAndTecido(confeccaoRoupa.getRoupa(), confeccaoRoupa.getTecido())).thenReturn(true);

        CategoriaConflitoException excecao = assertThrows(CategoriaConflitoException.class, () -> {
            service.cadastrarconfeccaoRoupa(confeccaoRoupa);
        });

        assertEquals("A roupa já está relacionada com este tecido", excecao.getMessage());

        verify(repository, never()).save(any());
    }

    @Test
    void deveDeletarConfeccaoRoupaPorIdQuandoIdValido() {
        int id = 1;

        when(repository.existsById(id)).thenReturn(true);

        service.deletarConfeccaoRoupa(id);

        verify(repository).deleteById(id);
    }

    @Test
    void deveFalharDeletarConfeccaoRoupaPorIdQuandoIdInvalido() {
        Integer id = 999;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(CategoriaNaoEncontradaException .class, () -> service.deletarConfeccaoRoupa(id));
    }

    @Test
    void deveAtualizarConfeccaoroupaQuandoDadosValidos() {
        ItemEstoque tecidoEstoque = new ItemEstoque();
        tecidoEstoque.setIdItemEstoque(1);
        tecidoEstoque.setDescricao("descricao tecido");
        tecidoEstoque.setComplemento("complemento tecido");
        tecidoEstoque.setPeso(2.5);
        tecidoEstoque.setQtdMinimo(1.0);
        tecidoEstoque.setQtdArmazenado(10.0);

        ItemEstoque roupaEstoque = new ItemEstoque();
        roupaEstoque.setIdItemEstoque(2);
        roupaEstoque.setDescricao("descricao tecido");
        roupaEstoque.setComplemento("complemento tecido");
        roupaEstoque.setPeso(2.5);
        roupaEstoque.setQtdMinimo(1.0);
        roupaEstoque.setQtdArmazenado(10.0);

        ConfeccaoRoupa confeccaoRoupa = new ConfeccaoRoupa(
                1,
                roupaEstoque,
                tecidoEstoque,
                1.0
        );

        when(repository.existsById(confeccaoRoupa.getIdConfeccaoRoupa())).thenReturn(true);
        confeccaoRoupa.setQtdTecido(10.0);
        ConfeccaoRoupa confeccaoSalvar = service.atualizarConfeccaoRoupa(confeccaoRoupa.getIdConfeccaoRoupa(), confeccaoRoupa);
        when(repository.save(confeccaoSalvar));

        assertEquals(confeccaoRoupa.getIdConfeccaoRoupa(), confeccaoSalvar.getIdConfeccaoRoupa());
        assertEquals(confeccaoRoupa.getRoupa(), confeccaoSalvar.getRoupa());
        assertEquals(confeccaoRoupa.getTecido(), confeccaoSalvar.getTecido());
        assertEquals(confeccaoRoupa.getQtdTecido(), confeccaoSalvar.getQtdTecido());
    }

    @Test
    void deveFalharAtualizarConfeccaoroupaQuandoIdInvalidos() {
        ItemEstoque tecidoEstoque = new ItemEstoque();
        tecidoEstoque.setIdItemEstoque(1);
        tecidoEstoque.setDescricao("descricao tecido");
        tecidoEstoque.setComplemento("complemento tecido");
        tecidoEstoque.setPeso(2.5);
        tecidoEstoque.setQtdMinimo(1.0);
        tecidoEstoque.setQtdArmazenado(10.0);

        ItemEstoque roupaEstoque = new ItemEstoque();
        roupaEstoque.setIdItemEstoque(2);
        roupaEstoque.setDescricao("descricao tecido");
        roupaEstoque.setComplemento("complemento tecido");
        roupaEstoque.setPeso(2.5);
        roupaEstoque.setQtdMinimo(1.0);
        roupaEstoque.setQtdArmazenado(10.0);

        ConfeccaoRoupa confeccaoRoupa = new ConfeccaoRoupa(
                999,
                roupaEstoque,
                tecidoEstoque,
                1.0
        );

        when(repository.existsById(confeccaoRoupa.getIdConfeccaoRoupa())).thenReturn(false);

        assertThrows(ConfeccaoRoupaNaoEncontradaException.class, () -> {
            service.atualizarConfeccaoRoupa(confeccaoRoupa.getIdConfeccaoRoupa(), confeccaoRoupa);
        });
    }
}