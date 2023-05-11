package br.com.sinqia.crud;


import br.com.sinqia.dtos.ClienteDTO;
import br.com.sinqia.dtos.ClienteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteCrud implements Crud<ClienteDTO> {

    private Connection connection;

    public ClienteCrud(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ClienteDTO clienteDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT Cliente (Nome) " +
                        "VALUES(?)"
        );

        preparedStatement.setString(1, clienteDTO.getNome());
        preparedStatement.executeUpdate();
    }

    @Override
    public ClienteDTO read(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT Nome FROM Cliente WHERE Id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        ClienteDTO clienteDTO = new ClienteDTO();
        while (rs.next()) {
            clienteDTO.setNome(rs.getString("Nome"));
        }
        return clienteDTO;
    }

    @Override
    public void update(int id, ClienteDTO clienteDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Cliente " +
                        "SET Nome = ? " +
                        "WHERE Id = ?"
        );
        preparedStatement.setString(1, clienteDTO.getNome());
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Cliente " +
                        "WHERE Id = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

}
