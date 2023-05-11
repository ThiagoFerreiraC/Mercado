package br.com.sinqia.validacoes;

import br.com.sinqia.MySQLConnection;
import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.exceptions.ProdutoInvalidException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidacaoItemDoPedidoExistente implements ValidacaoItemDoPedido {
    @Override
    public void validar(ItemDoPedido itemDoPedido) {
        try {
            realizarValidacao(itemDoPedido);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private void realizarValidacao(ItemDoPedido itemDoPedido) throws SQLException {
        Connection connection = MySQLConnection.getInstance();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(Id) FROM Produto " +
                        "WHERE Id = ?"
        );

        preparedStatement.setInt(1, itemDoPedido.getProdutoId());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getInt(1) == 0) {
                throw new ProdutoInvalidException("Produto não encontrado");
            }
        }
    }
}
