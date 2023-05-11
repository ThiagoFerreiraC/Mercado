package br.com.sinqia.crud;

import br.com.sinqia.MySQLConnection;
import br.com.sinqia.dtos.PedidoClienteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoClienteCrud implements Crud<PedidoClienteDTO> {

    private Connection connection;

    public PedidoClienteCrud(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(PedidoClienteDTO pedidoClienteDTO) throws SQLException {
        Connection connection = MySQLConnection.getInstance();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT Pedido (Id, Cliente_id) " +
                        " VALUES(?,?)"
        );

        preparedStatement.setString(1, pedidoClienteDTO.getChaveId());
        preparedStatement.setInt(2, pedidoClienteDTO.getClienteId());
        preparedStatement.executeUpdate();
    }

    @Override
    public PedidoClienteDTO read(int id) throws SQLException {
        return null;
    }

    public PedidoClienteDTO read(String chave) throws SQLException {
        PedidoClienteDTO pedidoClienteDTO = new PedidoClienteDTO();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Pedido_CLiente " +
                        " WHERE Pedido_id = ?"
        );
        preparedStatement.setString(1, chave);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            pedidoClienteDTO.setClienteId(resultSet.getInt("Cliente_id"));
        }
        return pedidoClienteDTO;
    }


    @Override
    public void update(int id, PedidoClienteDTO pedidoClienteDTO) throws SQLException {

    }

    public void update(String chave, PedidoClienteDTO pedidoClienteDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Pedido " +
                        "SET " +
                        "   Cliente_Id = ? " +
                        "WHERE Id = ? "
        );

        preparedStatement.setInt(1, pedidoClienteDTO.getClienteId());
        preparedStatement.setString(2, chave);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    public void delete(String chave) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Pedido " +
                        "WHERE Id = ? "
        );

        preparedStatement.setString(1, chave);
        preparedStatement.executeUpdate();
    }
}
