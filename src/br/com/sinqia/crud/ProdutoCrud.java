package br.com.sinqia.crud;


import br.com.sinqia.dtos.ProdutoDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoCrud implements Crud<ProdutoDTO> {

    private Connection connection;

    public ProdutoCrud(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ProdutoDTO produtoDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT Produto (Nome, Preco, Categoria_id) " +
                        " VALUES(?, ?, ?)"
        );
        preparedStatement.setString(1, produtoDTO.getNome());
        preparedStatement.setBigDecimal(2, produtoDTO.getPreco());
        preparedStatement.setInt(3, produtoDTO.getCategoriaId());

        preparedStatement.executeUpdate();
    }

    @Override
    public ProdutoDTO read(int id) throws SQLException {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Produto " +
                        "WHERE Id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            produtoDTO.setNome(rs.getString("Nome"));
            produtoDTO.setPreco( rs.getBigDecimal("Preco").setScale(2, RoundingMode.CEILING));
        }
        return produtoDTO;
    }


    @Override
    public void update(int id, ProdutoDTO produtoDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Produto " +
                        "SET " +
                        "Nome = ?, " +
                        "Preco = ?, " +
                        "Categoria_Id = ? " +
                        "WHERE Id = ?"
        );
        preparedStatement.setString(1, produtoDTO.getNome());
        preparedStatement.setBigDecimal(2, produtoDTO.getPreco());
        preparedStatement.setInt(3, produtoDTO.getCategoriaId());
        preparedStatement.setInt(4, id);

        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Produto " +
                        "WHERE Id = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public List<Integer> listAllIds() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT Id FROM Produto"
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Integer> listaInteiros = new ArrayList<>();
        while(resultSet.next()) {
            listaInteiros.add(resultSet.getInt("Id"));
        }
        return listaInteiros;
    }
}
