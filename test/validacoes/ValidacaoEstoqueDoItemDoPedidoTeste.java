package validacoes;

import br.com.sinqia.crud.EstoqueCrud;
import br.com.sinqia.dtos.EstoqueDTO;
import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.exceptions.EstoqueInsuficienteException;
import br.com.sinqia.validacoes.ValidacaoEstoqueDoItemDoPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ValidacaoEstoqueDoItemDoPedidoTeste {

    private ValidacaoEstoqueDoItemDoPedido validacao;

    private EstoqueCrudMock estoqueCrudMock;

    @BeforeEach
    public void inicializar() {
        this.estoqueCrudMock = new EstoqueCrudMock();
        this.validacao = new ValidacaoEstoqueDoItemDoPedido(estoqueCrudMock);
    }

    @Test
    public void dadoEstoqueDoItemMenorQueQuantidadeSolicitada_DeveGerarException() {
        ItemDoPedido itemDoPedido = new ItemDoPedido(543, 10);
        estoqueCrudMock.setQuantidadeNoEstoqueDTO(1);

        Assertions.assertThrowsExactly(EstoqueInsuficienteException.class,
                () -> {validacao.validar(itemDoPedido);});
    }

    @Test
    public void dadoEstoqueDoItemMaiorQueQuantidadeSolicitada_ValidarComSucesso() {
        ItemDoPedido itemDoPedido = new ItemDoPedido(543, 10);
        estoqueCrudMock.setQuantidadeNoEstoqueDTO(1000);

        Assertions.assertDoesNotThrow(() -> {validacao.validar(itemDoPedido);});
    }

    @Test
    public void dadoEstoqueDoItemIgualAQuantidadeSolicitada_ValidarComSucesso() {
        ItemDoPedido itemDoPedido = new ItemDoPedido(543, 10);
        estoqueCrudMock.setQuantidadeNoEstoqueDTO(10);

        Assertions.assertDoesNotThrow(() -> {validacao.validar(itemDoPedido);});
    }
}

class EstoqueCrudMock extends EstoqueCrud {

    private EstoqueDTO estoqueDTO = new EstoqueDTO();

    public EstoqueCrudMock() {
        super(null);
    }

    public void setQuantidadeNoEstoqueDTO(int quantidadeEmEstoque) {
        this.estoqueDTO.setQuantidadeEmEstoque(quantidadeEmEstoque);
    }

    @Override
    public EstoqueDTO read(int id) {
        return estoqueDTO;
    }
}