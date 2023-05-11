package validacoes;

import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.exceptions.ProdutoInvalidException;
import br.com.sinqia.validacoes.ValidacaoItemDoPedido;
import br.com.sinqia.validacoes.ValidacaoItemDoPedidoExistente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ValidacaoItemDoPedidoExistenteTeste {

    private ValidacaoItemDoPedido validacao;

    @BeforeEach
    public void inicializar() {
        this.validacao = new ValidacaoItemDoPedidoExistente();
    }

    @Test
    public void dadoProdutoIdInvalidaGerarException() {
        ItemDoPedido itemDoPedido = new ItemDoPedido(-79, 3);
        Assertions.assertThrows(ProdutoInvalidException.class, () -> {
            validacao.validar(itemDoPedido);
        });
    }

    @Test
    public void dadoProdutoIdValidoNaoDeveGerarException() {
        ItemDoPedido itemDoPedido = new ItemDoPedido(1, 3);
        Assertions.assertDoesNotThrow(() -> {
            validacao.validar(itemDoPedido);
        });
    }

}
