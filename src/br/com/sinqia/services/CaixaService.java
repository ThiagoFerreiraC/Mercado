package br.com.sinqia.services;

import br.com.sinqia.Carrinho;
import br.com.sinqia.crud.ProdutoCrud;
import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.dtos.ItemProcessadoDTO;
import br.com.sinqia.dtos.ProdutoDTO;
import br.com.sinqia.exceptions.CarrinhoInvalidException;
import br.com.sinqia.validacoes.ValidacaoItemDoPedido;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CaixaService {

    private ProdutoCrud produtoCrud;
    private List<ValidacaoItemDoPedido> validacoes;

    public CaixaService(ProdutoCrud produtoCrud, List<ValidacaoItemDoPedido> validacoes) {
        this.produtoCrud = produtoCrud;
        this.validacoes = validacoes;
    }

    public List<ItemProcessadoDTO> processarItensDoPedido(Carrinho carrinho) {
        if (carrinho == null || carrinho.getPedido().isEmpty()) {
            throw new CarrinhoInvalidException("Carrinho não encontrado");
        }

        realizarValidacoes(carrinho);

        return carrinho.getPedido().stream()
                .map(itemDoPedido -> {
                    ItemProcessadoDTO itemProcessadoDTO = new ItemProcessadoDTO();
                    itemProcessadoDTO.setQuantidade(itemDoPedido.getQuantidade());
                    ProdutoDTO produtoDTO = pegarProduto(itemDoPedido.getProdutoId());
                    itemProcessadoDTO.setNomeProduto(produtoDTO.getNome());
                    itemProcessadoDTO.setValorUnitario(produtoDTO.getPreco());
                    itemProcessadoDTO.setValorTotal(calcularPrecoTotalPorItem(itemProcessadoDTO));
                    return itemProcessadoDTO;
                }).collect(Collectors.toList());
    }

    private void realizarValidacoes(Carrinho carrinho) {
        carrinho.getPedido().forEach(pedidoItem -> {
            validacoes.forEach(validacao -> {
                    validacao.validar(pedidoItem);
            });
        });
    }

    private BigDecimal calcularPrecoTotalPorItem(ItemProcessadoDTO item) {
        return item.getValorUnitario().multiply(new BigDecimal(item.getQuantidade()));
    }

    public ProdutoDTO pegarProduto(int id) {
        try {
            return produtoCrud.read(id);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public BigDecimal calcularTotalCompra(List<ItemProcessadoDTO> itemProcessadoDTOS) {
        return itemProcessadoDTOS
                .stream()
                .map(ItemProcessadoDTO::getValorTotal)
                .reduce(BigDecimal::add)
                .get();
    }
}
