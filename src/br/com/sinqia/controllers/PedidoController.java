package br.com.sinqia.controllers;

import br.com.sinqia.Carrinho;
import br.com.sinqia.GerarChave;
import br.com.sinqia.dtos.PedidoClienteDTO;
import br.com.sinqia.dtos.PedidoProdutoDTO;
import br.com.sinqia.services.PedidoService;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoController {
    private String chave;
    //TODO talvez passar pelo construtor
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public void persistirPedidoClienteNaBaseDeDados(Carrinho carrinho) {
        chave = new GerarChave().gerarChave();
        PedidoClienteDTO pedidoClienteDTO = new PedidoClienteDTO();
        pedidoClienteDTO.setClienteId(carrinho.getCliente().getId());
        pedidoClienteDTO.setChaveId(chave);
        pedidoService.persistirPedidoCliente(pedidoClienteDTO);
    }

    public void persistirPedidoProdutoNaBaseDeDados(Carrinho carrinho) {
        List<PedidoProdutoDTO> pedidoProdutoDTOLista = carrinho.getPedido().stream().
                map(itemDoPedido -> {
                    PedidoProdutoDTO pedidoProdutoDTO = new PedidoProdutoDTO();
                    pedidoProdutoDTO.setPedidoId(chave);
                    pedidoProdutoDTO.setProdutoId(itemDoPedido.getProdutoId());
                    pedidoProdutoDTO.setQuantidade(itemDoPedido.getQuantidade());
                    return pedidoProdutoDTO;
                }).collect(Collectors.toList());
        pedidoService.persistirPedidoProduto(pedidoProdutoDTOLista);
    }
}
