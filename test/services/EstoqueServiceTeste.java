package services;

import br.com.sinqia.crud.EstoqueCrud;
import br.com.sinqia.dtos.EstoqueDTO;
import br.com.sinqia.exceptions.IdInvalidException;
import br.com.sinqia.services.EstoqueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EstoqueServiceTeste {

    private EstoqueService estoqueService;

    @BeforeEach
    public void inicializar() {
        this.estoqueService = new EstoqueService(new EstoqueCrudMock());
    }

    @Test
    public void dadoEstoqueDTOValido_CalculaEstoqueDoProdutoComSucesso() {
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setQuantidadeEmEstoque(100);
        estoqueDTO.setQuantidadeDoPedido(30);
        Assertions.assertEquals(70, estoqueService.calcularEstoqueDoProduto(estoqueDTO));
    }

    @Test
    public void dadoProdutoIdInvalida_LeituraDoEstoqueGeraException() {
        Assertions.assertThrowsExactly(IdInvalidException.class, () -> {
            estoqueService.read(-10);
        });
    }

    public void dadoProdutoIdInvalido_AtualizacaoDoEstoqueGeraException() {
        Assertions.assertThrowsExactly(IdInvalidException.class, () -> {
            estoqueService.atualizarEstoque(-10, new EstoqueDTO());
        });
    }
}

class EstoqueCrudMock extends EstoqueCrud {

    public EstoqueCrudMock() {
        super(null);
    }
}

