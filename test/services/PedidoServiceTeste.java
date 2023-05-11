package services;

import br.com.sinqia.crud.PedidoClienteCrud;
import br.com.sinqia.crud.PedidoProdutoCrud;
import br.com.sinqia.exceptions.PedidoClienteNotFoundException;
import br.com.sinqia.exceptions.PedidoProdutoInvalidException;
import br.com.sinqia.services.PedidoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class PedidoServiceTeste {

    private PedidoService pedidoService;

    @BeforeEach
    public void inicializar() {
        this.pedidoService = new PedidoService(new PedidoClienteCrudMock(), new PedidoProdutoCrudMock());
    }

    @Test
    public void dadoListaDePedidoProdutoNula_GerarException() {
        Assertions.assertThrowsExactly(PedidoProdutoInvalidException.class,
                () -> {
                    pedidoService.persistirPedidoProduto(null);
                });
    }

    @Test
    public void dadoListaDePedidoProdutoVazia_GerarException() {
        Assertions.assertThrowsExactly(PedidoProdutoInvalidException.class,
                () -> {
                    pedidoService.persistirPedidoProduto(new ArrayList<>());
                });
    }

    @Test
    public void dadoPedidoClienteDTONulo_GerarException() {
        Assertions.assertThrowsExactly(PedidoClienteNotFoundException.class, () -> {
            pedidoService.persistirPedidoCliente(null);
        });
    }
}

class PedidoClienteCrudMock extends PedidoClienteCrud {
    public PedidoClienteCrudMock() {
        super(null);
    }
}

class PedidoProdutoCrudMock extends PedidoProdutoCrud {
    public PedidoProdutoCrudMock() {
        super(null);
    }
}