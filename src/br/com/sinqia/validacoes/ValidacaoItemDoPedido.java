package br.com.sinqia.validacoes;

import br.com.sinqia.dtos.ItemDoPedido;

public interface ValidacaoItemDoPedido extends Validacao<ItemDoPedido> {

    @Override
    void validar(ItemDoPedido itemDoPedido);
}
