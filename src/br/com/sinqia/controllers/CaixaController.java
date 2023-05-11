package br.com.sinqia.controllers;

import br.com.sinqia.Carrinho;
import br.com.sinqia.dtos.ItemProcessadoDTO;
import br.com.sinqia.services.CaixaService;

import java.math.BigDecimal;
import java.util.List;

public class CaixaController {

    private CaixaService caixaService;

    public CaixaController(CaixaService caixaService) {
        this.caixaService = caixaService;
    }

    public List<ItemProcessadoDTO> processarItensDoPedido(Carrinho carrinho) {
        return caixaService.processarItensDoPedido(carrinho);
    }

    public BigDecimal calcularTotalDaCompra(List<ItemProcessadoDTO> itemProcessadoDTOS) {
        return caixaService.calcularTotalCompra(itemProcessadoDTOS);
    }

}
