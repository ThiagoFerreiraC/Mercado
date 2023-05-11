package br.com.sinqia.crud;


import br.com.sinqia.dtos.CategoriaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaCrud implements Crud<CategoriaDTO> {

    private Connection connection;

    public CategoriaCrud(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CategoriaDTO categoriaDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT Categoria (Descricao) " +
                        "VALUES(?)"
        );

        preparedStatement.setString(1, categoriaDTO.getDescricao());
        preparedStatement.executeUpdate();
    }

    @Override
    public CategoriaDTO read(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT Descricao FROM Categoria WHERE Id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        while (rs.next()) {
            categoriaDTO.setDescricao(rs.getString("Descricao"));
        }
        return categoriaDTO;
    }

    @Override
    public void update(int id, CategoriaDTO categoriaDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Categoria " +
                        "SET Descricao = ? " +
                        "WHERE Id = ?"
        );
        preparedStatement.setString(1, categoriaDTO.getDescricao());
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Categoria " +
                        "WHERE Id = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

}
