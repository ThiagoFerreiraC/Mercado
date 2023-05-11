package br.com.sinqia.validacoes;

import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.exceptions.QuantidadeProdutoInvalidException;

public class ValidacaoQuantidadeProdutoAdicionadaDoPedido implements ValidacaoItemDoPedido {
    @Override
    public void validar(ItemDoPedido itemDoPedido) {
        if (itemDoPedido.getQuantidade() <= 0) {
            throw new QuantidadeProdutoInvalidException("Quantidade do Produto é inválida");
        }
    }
}
