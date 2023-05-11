package br.com.sinqia;



import br.com.sinqia.dtos.ItemProcessadoDTO;

import java.math.BigDecimal;

public class DemonstrativoCompra {

    public void exibirTotalPorItem(ItemProcessadoDTO item) {
        System.out.println(item.getNomeProduto() +
                ": R$" +
                item.getValorTotal());
    }

    public void exibirPrecosUnitarios(ItemProcessadoDTO item) {
        System.out.println("\t\t" +
                item.getQuantidade() +
                " x " +
                item.getValorUnitario());
    }

    public void exibirTotalCompra(BigDecimal totalCompra) {
        System.out.println("Total: R$" + totalCompra);
    }
}