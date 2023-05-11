package br.com.sinqia;

import br.com.sinqia.dtos.EstoquePorCategoriaDTO;

import java.util.List;

public class DemonstrativoEstoque {

    public void exibirTotalDeItensNoEstoque(int totalDeItens) {
        System.out.println("---------- Estoque ---------");
        System.out.println("Total de Itens: " + totalDeItens);
    }

    public void exibirTotalDeItensNoEstoquePorCategoria(List<EstoquePorCategoriaDTO> estoquePorCategoriaDTOList) {
        estoquePorCategoriaDTOList.stream()
                .sorted(EstoquePorCategoriaDTO::compareTo)
                .forEach(item -> {
                            System.out.println(item.getCategoria() +
                                    ": " +
                                    item.getQuantidade());
                        }
                );
    }
}
