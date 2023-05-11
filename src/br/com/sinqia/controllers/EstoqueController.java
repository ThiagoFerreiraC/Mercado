package br.com.sinqia.controllers;

import br.com.sinqia.dtos.EstoqueDTO;
import br.com.sinqia.dtos.EstoquePorCategoriaDTO;
import br.com.sinqia.services.EstoqueService;

import java.util.List;

public class EstoqueController {

    private EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    public EstoqueDTO read(int id) {
        return estoqueService.read(id);
    }

    public int calcularEstoqueDoProduto(EstoqueDTO estoqueDTO) {
        return estoqueService.calcularEstoqueDoProduto(estoqueDTO);
    }

    public void atualizarEstoque(EstoqueDTO estoqueDTO) {
        estoqueService.atualizarEstoque(estoqueDTO.getProdutoId(), estoqueDTO);
    }


    public int pegarTotaldeItensNoEstoque() {
        return estoqueService.pegarTotaldeItensNoEstoque();
    }

    public List<EstoquePorCategoriaDTO> pegarTotalDeItensPorCategoria() {
        return estoqueService.pegarTotalDeItensPorCategoria();
    }
}
