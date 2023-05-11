package br.com.sinqia.services;

import br.com.sinqia.crud.PedidoClienteCrud;
import br.com.sinqia.crud.PedidoProdutoCrud;
import br.com.sinqia.dtos.PedidoClienteDTO;
import br.com.sinqia.dtos.PedidoProdutoDTO;
import br.com.sinqia.exceptions.PedidoClienteNotFoundException;
import br.com.sinqia.exceptions.PedidoProdutoInvalidException;

import java.sql.SQLException;
import java.util.List;

public class PedidoService {
    private PedidoClienteCrud pedidoClienteCrud;
    private PedidoProdutoCrud pedidoProdutoCrud;

    public PedidoService(PedidoClienteCrud pedidoClienteCrud, PedidoProdutoCrud pedidoProdutoCrud) {
        this.pedidoClienteCrud = pedidoClienteCrud;
        this.pedidoProdutoCrud = pedidoProdutoCrud;
    }

    public void persistirPedidoCliente(PedidoClienteDTO pedidoClienteDTO) {
        if (pedidoClienteDTO == null) {
            throw new PedidoClienteNotFoundException("Relação pedido-cliente não encontrada");
        }
        try {
            pedidoClienteCrud.create(pedidoClienteDTO);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void persistirPedidoProduto(List<PedidoProdutoDTO> pedidoProdutoDTOLista) {

        if (pedidoProdutoDTOLista == null || pedidoProdutoDTOLista.isEmpty()) {
            throw new PedidoProdutoInvalidException("Lista de relação pedido-produto não encontrada");
        }
        //TODO criar classe para validar ProdutoDTO

        pedidoProdutoDTOLista.forEach(pedidoProdutoDTO -> {
            try {
                pedidoProdutoCrud.create(pedidoProdutoDTO);
            } catch (SQLException e) {
                throw new RuntimeException();
            }});
    }
}
