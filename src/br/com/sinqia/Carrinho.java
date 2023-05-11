package br.com.sinqia;

import br.com.sinqia.dtos.ClienteDTO;
import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.exceptions.PedidoNotFoundException;
import br.com.sinqia.validacoes.ValidacaoItemDoPedido;

import java.util.List;
import java.util.Set;

public class Carrinho {

    private List<ValidacaoItemDoPedido> validacoes;
    private ClienteDTO cliente;
    private Set<ItemDoPedido> pedido;

    public Carrinho(List<ValidacaoItemDoPedido> validacoes) {
        this.validacoes = validacoes;
    }

    public void adicionarProdutos(Set<ItemDoPedido> pedido) {
        if (pedido == null || pedido.isEmpty()) {
            throw new PedidoNotFoundException("Pedido não encontrado");
        }
        validarProdutosAdicionados(pedido);
        this.setPedido(pedido);
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public Set<ItemDoPedido> getPedido() {
        return pedido;
    }

    public void setPedido(Set<ItemDoPedido> pedido) {
        this.pedido = pedido;
    }

    public void validarProdutosAdicionados(Set<ItemDoPedido> pedido) {
        pedido.forEach(pedidoItem -> {
            validacoes.forEach(validacao -> {
                validacao.validar(pedidoItem);
            });
        });
    }
}
