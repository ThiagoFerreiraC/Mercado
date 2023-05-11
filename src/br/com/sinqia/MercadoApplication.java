package br.com.sinqia;

import br.com.sinqia.controllers.*;

import br.com.sinqia.dtos.ClienteDTO;
import br.com.sinqia.dtos.EstoquePorCategoriaDTO;
import br.com.sinqia.dtos.ItemProcessadoDTO;


import javax.swing.text.View;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;


public class MercadoApplication {

    public static void main(String[] args){
        Connection connection = MySQLConnection.getInstance();
        Configuracao configuracao = new Configuracao();
        Carrinho carrinho = configuracao.gerarCarrinho();
        EstoqueController estoqueController = configuracao.gerarEstoqueController(connection);
        PedidoController pedidoController = configuracao.gerarPedidoController(connection);
        CaixaController caixaController = configuracao.gerarCaixaController(connection);
        ViewController viewController = configuracao.gerarViewController();

        configurarPedido(carrinho, configuracao);


        MercadoController mercadoController = configuracao.gerarMercadoController(carrinho);

        List<ItemProcessadoDTO> itemProcessadoDTOS = mercadoController.processarItensIndividualmente(caixaController);
        viewController.exibirItensDaCompra(itemProcessadoDTOS);

        BigDecimal totalCompra = mercadoController.processarTotalCompra(caixaController);

        viewController.exibirTotalCompra(totalCompra);
        mercadoController.processarAtualizacaoEstoque(estoqueController);

        pedidoController.persistirPedidoClienteNaBaseDeDados(carrinho);
        pedidoController.persistirPedidoProdutoNaBaseDeDados(carrinho);

        int totaldeItensNoEstoque = estoqueController.pegarTotaldeItensNoEstoque();
        viewController.exibirTotalDeItensDoEstoque(totaldeItensNoEstoque);

        List<EstoquePorCategoriaDTO> estoquePorCategoriaDTOList = estoqueController.pegarTotalDeItensPorCategoria();
        viewController.exibirTotalDeItensNoEstoquePorCategoria(estoquePorCategoriaDTOList);
    }

    public static void configurarPedido(Carrinho carrinho, Configuracao configuracao) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(80);
        carrinho.setCliente(clienteDTO);
        carrinho.adicionarProdutos(configuracao.gerarPedido());
    }

}
