package br.com.sinqia.crud;

import br.com.sinqia.MySQLConnection;
import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.dtos.PedidoProdutoDTO;
import br.com.sinqia.dtos.ProdutoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoProdutoCrud implements Crud<PedidoProdutoDTO> {

    private Connection connection;

    public PedidoProdutoCrud(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(PedidoProdutoDTO pedidoProdutoDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT Pedido_Produto (Pedido_Id, Produto_Id, Quantidade) " +
                        "VALUES (?, ?, ?)"
        );

        preparedStatement.setString(1, pedidoProdutoDTO.getPedidoId());
        preparedStatement.setInt(2, pedidoProdutoDTO.getProdutoId());
        preparedStatement.setInt(3, pedidoProdutoDTO.getQuantidade());
        preparedStatement.executeUpdate();
    }

    @Override
    public PedidoProdutoDTO read(int id) throws SQLException {
        return null;
    }

    public List<PedidoProdutoDTO> read(String chave) throws SQLException {
        List<PedidoProdutoDTO> pedidoProdutoDTOList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT Produto_Id, Quantidade FROM Pedido_Produto" +
                        " WHERE Pedido_Id = ?"
        );

        preparedStatement.setString(1, chave);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            PedidoProdutoDTO pedidoProdutoDTO = new PedidoProdutoDTO();
            pedidoProdutoDTO.setProdutoId(resultSet.getInt("Produto_Id"));
            pedidoProdutoDTO.setQuantidade(resultSet.getInt("Quantidade"));
            pedidoProdutoDTOList.add(pedidoProdutoDTO);
        }
        return pedidoProdutoDTOList;
    }


    public void update(String chave, PedidoProdutoDTO pedidoProdutoDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Pedido_Produto " +
                        "SET " +
                        "   Quantidade = ? " +
                        "WHERE Pedido_Id = ? AND Produto_Id = ?"
        );

        preparedStatement.setInt(1, pedidoProdutoDTO.getQuantidade());
        preparedStatement.setString(2, chave);
        preparedStatement.setInt(3, pedidoProdutoDTO.getProdutoId());
        preparedStatement.executeUpdate();

    }

    public void update(int id, PedidoProdutoDTO pedidoProdutoDTO) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    public void delete(String chave) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Pedido_Produto " +
                        "WHERE Id = ?"
        );

        preparedStatement.setString(1, chave);
        preparedStatement.executeUpdate();
    }
}
