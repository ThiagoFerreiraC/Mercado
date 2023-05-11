package br.com.sinqia.controllers;

import br.com.sinqia.DemonstrativoCompra;
import br.com.sinqia.DemonstrativoEstoque;
import br.com.sinqia.dtos.EstoquePorCategoriaDTO;
import br.com.sinqia.dtos.ItemProcessadoDTO;

import java.math.BigDecimal;
import java.util.List;

public class ViewController {

    private DemonstrativoEstoque demonstrativoEstoque;
    private DemonstrativoCompra demonstrativoCompra;

    public ViewController(DemonstrativoEstoque demonstrativoEstoque, DemonstrativoCompra demonstrativoCompra) {
        this.demonstrativoEstoque = demonstrativoEstoque;
        this.demonstrativoCompra = demonstrativoCompra;
    }

    public void exibirItensDaCompra(List<ItemProcessadoDTO> itemProcessadoDTOS) {
        itemProcessadoDTOS.forEach( itemProcessadoDTO -> {
            demonstrativoCompra.exibirTotalPorItem(itemProcessadoDTO);
            demonstrativoCompra.exibirPrecosUnitarios(itemProcessadoDTO);
        });
    }

    public void exibirTotalCompra(BigDecimal totalCompra) {
        demonstrativoCompra.exibirTotalCompra(totalCompra);
    }

    public void exibirTotalDeItensDoEstoque(int totaldeItensNoEstoque) {
        demonstrativoEstoque.exibirTotalDeItensNoEstoque(totaldeItensNoEstoque);
    }

    public void exibirTotalDeItensNoEstoquePorCategoria(List<EstoquePorCategoriaDTO> estoquePorCategoriaDTOList) {
        demonstrativoEstoque.exibirTotalDeItensNoEstoquePorCategoria(estoquePorCategoriaDTOList);
    }

}
