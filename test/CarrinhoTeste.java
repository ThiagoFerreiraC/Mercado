import br.com.sinqia.Carrinho;
import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.exceptions.PedidoNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarrinhoTeste {

    private Carrinho carrinho;

    @BeforeEach
    public void inicializar() {
        this.carrinho = new Carrinho(List.of());
    }

    @Test
    public void dadoPedidoNulo_GerarException() {
        Assertions.assertThrowsExactly(PedidoNotFoundException.class, () -> {carrinho.adicionarProdutos(null);});
    }


    @Test
    public void dadoPedidoVazio_GerarException() {
        Assertions.assertThrowsExactly(PedidoNotFoundException.class, () -> {carrinho.adicionarProdutos(new HashSet<>());});
    }

    @Test
    public void dadoItemDoPedido_AdicionarItensComSucesso() {
        Set<ItemDoPedido> itemDoPedidoSet = new HashSet<>();
        ItemDoPedido item = new ItemDoPedido(2, 5);
        itemDoPedidoSet.add(item);
        carrinho.adicionarProdutos(itemDoPedidoSet);
        Assertions.assertEquals(true, carrinho.getPedido().contains(item));
    }
}
