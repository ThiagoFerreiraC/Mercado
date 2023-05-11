package br.com.sinqia.validacoes;

import br.com.sinqia.MySQLConnection;
import br.com.sinqia.crud.EstoqueCrud;
import br.com.sinqia.dtos.EstoqueDTO;
import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.exceptions.EstoqueInsuficienteException;
import java.sql.SQLException;

public class ValidacaoEstoqueDoItemDoPedido implements ValidacaoItemDoPedido {

    private EstoqueCrud estoqueCrud;

    public ValidacaoEstoqueDoItemDoPedido(EstoqueCrud estoqueCrud) {
        this.estoqueCrud = estoqueCrud;
    }

    @Override
    public void validar(ItemDoPedido itemDoPedido) {
        try {
            EstoqueDTO estoqueDTO = estoqueCrud.read(itemDoPedido.getProdutoId());
            if (itemDoPedido.getQuantidade() > estoqueDTO.getQuantidadeEmEstoque()) {
                throw new EstoqueInsuficienteException("Estoque do produto é insuficiente");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}