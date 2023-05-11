package br.com.sinqia.services;

import br.com.sinqia.crud.EstoqueCrud;
import br.com.sinqia.dtos.EstoqueDTO;
import br.com.sinqia.dtos.EstoquePorCategoriaDTO;
import br.com.sinqia.exceptions.IdInvalidException;

import java.sql.SQLException;
import java.util.List;

public class EstoqueService {
    private EstoqueCrud estoqueCrud;

    public EstoqueService(EstoqueCrud estoqueCrud) {
        this.estoqueCrud = estoqueCrud;
    }

    public EstoqueDTO read(int id) {
        if (id <= 0) {
            throw new IdInvalidException("Id do Produto inválida");
        }
        try {
            return estoqueCrud.read(id);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public void atualizarEstoque(int produtoId, EstoqueDTO estoqueDTO) {
        if (produtoId <= 0) {
            throw new IdInvalidException("Id do Produto inválida");
        }

        try {
            estoqueCrud.update(produtoId, estoqueDTO);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public int calcularEstoqueDoProduto(EstoqueDTO estoqueDTO) {
        return estoqueDTO.getQuantidadeEmEstoque() - estoqueDTO.getQuantidadeDoPedido();
    }

    public int pegarTotaldeItensNoEstoque() {
        try {
            return estoqueCrud.countAll();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<EstoquePorCategoriaDTO> pegarTotalDeItensPorCategoria(){
        try {
            return estoqueCrud.countByCategory();
        }  catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
