package br.com.sinqia;

import br.com.sinqia.controllers.*;
import br.com.sinqia.crud.EstoqueCrud;
import br.com.sinqia.crud.PedidoClienteCrud;
import br.com.sinqia.crud.PedidoProdutoCrud;
import br.com.sinqia.crud.ProdutoCrud;
import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.services.CaixaService;
import br.com.sinqia.services.EstoqueService;
import br.com.sinqia.services.PedidoService;
import br.com.sinqia.validacoes.ValidacaoEstoqueDoItemDoPedido;
import br.com.sinqia.validacoes.ValidacaoItemDoPedido;
import br.com.sinqia.validacoes.ValidacaoItemDoPedidoExistente;
import br.com.sinqia.validacoes.ValidacaoQuantidadeProdutoAdicionadaDoPedido;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public class Configuracao {

    public Carrinho gerarCarrinho() {
        List<ValidacaoItemDoPedido> validacoes = List.of(
                new ValidacaoItemDoPedidoExistente(),
                new ValidacaoQuantidadeProdutoAdicionadaDoPedido()
        );
        return new Carrinho(validacoes);
    }

    public Set<ItemDoPedido> gerarPedido() {
        return Set.of(
                new ItemDoPedido(513, 1),
                new ItemDoPedido(514, 2),
                new ItemDoPedido(550, 2)
        );
    }

    public EstoqueController gerarEstoqueController(Connection connection) {
        return new EstoqueController(new EstoqueService(new EstoqueCrud(connection)));
    }

    public PedidoController gerarPedidoController(Connection connection) {
        return new PedidoController(
                new PedidoService(
                        new PedidoClienteCrud(connection),
                        new PedidoProdutoCrud(connection))
        );
    }

    public CaixaController gerarCaixaController(Connection connection) {
        List<ValidacaoItemDoPedido> validacoes = List.of(
                new ValidacaoEstoqueDoItemDoPedido(new EstoqueCrud(connection))
        );
        return new CaixaController(
                new CaixaService(
                        new ProdutoCrud(connection),
                        validacoes));
    }

    public ViewController gerarViewController() {
        DemonstrativoEstoque demonstrativoEstoque = new DemonstrativoEstoque();
        DemonstrativoCompra demonstrativoCompra = new DemonstrativoCompra();
        return new ViewController(demonstrativoEstoque, demonstrativoCompra);
    }

    public MercadoController gerarMercadoController(Carrinho carrinho) {
        return new MercadoController(carrinho);
    }
}
