package br.com.sinqia.controllers;

import br.com.sinqia.*;

import br.com.sinqia.dtos.EstoqueDTO;
import br.com.sinqia.dtos.EstoquePorCategoriaDTO;
import br.com.sinqia.dtos.ItemProcessadoDTO;


import java.math.BigDecimal;

import java.util.List;

public class MercadoController {
    private Carrinho carrinho;

    public MercadoController(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public  List<ItemProcessadoDTO> processarItensIndividualmente(CaixaController caixaController) {
        return caixaController.processarItensDoPedido(carrinho);
    }

    public BigDecimal processarTotalCompra(CaixaController caixaController) {
        List<ItemProcessadoDTO> itemProcessadoDTOS = caixaController.processarItensDoPedido(carrinho);
        return caixaController.calcularTotalDaCompra(itemProcessadoDTOS);
    }

    public void processarAtualizacaoEstoque(EstoqueController estoqueController) {
        carrinho.getPedido().forEach(itemDoPedido -> {
            EstoqueDTO estoqueDTO = estoqueController.read(itemDoPedido.getProdutoId());
            estoqueDTO.setProdutoId(itemDoPedido.getProdutoId());
            estoqueDTO.setQuantidadeDoPedido(itemDoPedido.getQuantidade());
            int estoqueAtualizado = estoqueController.calcularEstoqueDoProduto(estoqueDTO);
            estoqueDTO.setQuantidadeEmEstoque(estoqueAtualizado);
            estoqueController.atualizarEstoque(estoqueDTO);
        });
    }
}
