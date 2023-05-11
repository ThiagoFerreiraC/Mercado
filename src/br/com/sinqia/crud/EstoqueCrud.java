package br.com.sinqia.crud;

import br.com.sinqia.dtos.EstoqueDTO;
import br.com.sinqia.dtos.EstoquePorCategoriaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueCrud implements Crud<EstoqueDTO> {

    private Connection connection;

    public EstoqueCrud(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(EstoqueDTO estoqueDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT Estoque (Produto_Id, Quantidade) " +
                        "VALUES(? , ?)"
        );

        preparedStatement.setInt(1, estoqueDTO.getProdutoId());
        preparedStatement.setInt(2, estoqueDTO.getQuantidadeEmEstoque());
        preparedStatement.executeUpdate();
    }

    @Override
    public EstoqueDTO read(int id) throws SQLException {
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Estoque " +
                        "WHERE Produto_Id = ?"
        );

        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            estoqueDTO.setQuantidadeEmEstoque(rs.getInt("Quantidade"));
        }
        return estoqueDTO;
    }

    @Override
    public void update(int id, EstoqueDTO estoqueDTO) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Estoque " +
                        "SET " +
                        "Quantidade = ? " +
                        "WHERE Produto_Id = ?"
        );

        preparedStatement.setInt(1, estoqueDTO.getQuantidadeEmEstoque());
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Estoque " +
                        "WHERE Produto_Id = ?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public int countAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT SUM(Quantidade) FROM Estoque"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        int total = 0;
        while(resultSet.next()) {
            total = resultSet.getInt(1);
        }
        return total;
    }

    public List<EstoquePorCategoriaDTO> countByCategory() throws SQLException {
        List<EstoquePorCategoriaDTO> estoquePorCategoriaDTOList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT categoria.Descricao, SUM(estoque.Quantidade) AS Somatoria from categoria " +
                        "LEFT JOIN produto " +
                        "   ON produto.Categoria_Id = categoria.Id " +
                        "LEFT JOIN estoque " +
                        "   ON estoque.Produto_Id = produto.Id " +
                        "GROUP BY categoria.Descricao"
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            EstoquePorCategoriaDTO estoquePorCategoriaDTO = new EstoquePorCategoriaDTO();
            estoquePorCategoriaDTO.setCategoria(resultSet.getString("Descricao"));
            estoquePorCategoriaDTO.setQuantidade(resultSet.getInt("Somatoria"));
            estoquePorCategoriaDTOList.add(estoquePorCategoriaDTO);
        }
        return estoquePorCategoriaDTOList;
    }
}
