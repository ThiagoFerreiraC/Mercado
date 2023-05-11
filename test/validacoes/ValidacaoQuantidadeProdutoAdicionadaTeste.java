package validacoes;

import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.exceptions.QuantidadeProdutoInvalidException;
import br.com.sinqia.validacoes.ValidacaoItemDoPedido;
import br.com.sinqia.validacoes.ValidacaoQuantidadeProdutoAdicionadaDoPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidacaoQuantidadeProdutoAdicionadaTeste {
    private ValidacaoItemDoPedido validacao;

    @BeforeEach
    public void inicializar() {
        this.validacao = new ValidacaoQuantidadeProdutoAdicionadaDoPedido();
    }

    @Test
    public void dadoQuantidadeProdutoMenorQueZeroDeveGerarException() {
        ItemDoPedido itemDoPedido = new ItemDoPedido(1, -9);
        Assertions.assertThrows(QuantidadeProdutoInvalidException.class, () -> {
            validacao.validar(itemDoPedido);
        });
    }

    @Test
    public void dadoQuantidadeProdutoMaiorQueZeroNaoDeveGerarException() {
        ItemDoPedido itemDoPedido = new ItemDoPedido(1, 40);
        Assertions.assertDoesNotThrow(() -> {
            validacao.validar(itemDoPedido);
        });
    }
}
