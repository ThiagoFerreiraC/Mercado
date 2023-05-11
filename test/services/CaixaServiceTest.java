package services;

import br.com.sinqia.Carrinho;
import br.com.sinqia.crud.ProdutoCrud;
import br.com.sinqia.dtos.ItemDoPedido;
import br.com.sinqia.dtos.ItemProcessadoDTO;

import br.com.sinqia.dtos.ProdutoDTO;
import br.com.sinqia.exceptions.CarrinhoInvalidException;
import br.com.sinqia.services.CaixaService;
import br.com.sinqia.validacoes.ValidacaoItemDoPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CaixaServiceTest {

    private CaixaService caixaService;
    private Carrinho carrinho;
    private ProdutoCrudMock produtoCrudMock;

    @BeforeEach
    public void inicializar() {
        this.carrinho = new CarrinhoMock(List.of());
        this.produtoCrudMock = new ProdutoCrudMock();
        this.caixaService = new CaixaService(produtoCrudMock, List.of());
    }

    @Test
    public void dadoItemEQuantidade_CalcularTotalPorItemComSucesso() {
        produtoCrudMock.setPrecoProdutoDTO(new BigDecimal("50.00"));
        carrinho.adicionarProdutos(Set.of(new ItemDoPedido(2, 10)));
        List<ItemProcessadoDTO> itemProcessadoDTOS = caixaService.processarItensDoPedido(carrinho);
        Assertions.assertEquals(new BigDecimal("500.00"), itemProcessadoDTOS.get(0).getValorTotal());
    }

    @Test
    public void dadoSetDeItemDoPedido_DeveGerarListaComItemProcessado() {
        carrinho.adicionarProdutos(Set.of(
                new ItemDoPedido(1, 5),
                new ItemDoPedido(2, 3)));

        produtoCrudMock.setPrecoProdutoDTO(new BigDecimal("100.00"));

        ItemProcessadoDTO itemProcessadoDTO = new ItemProcessadoDTO();
        itemProcessadoDTO.setValorTotal(new BigDecimal("500.00"));
        itemProcessadoDTO.setValorUnitario(new BigDecimal("100.00"));
        itemProcessadoDTO.setQuantidade(5);
        itemProcessadoDTO.setNomeProduto(null);

        ItemProcessadoDTO itemProcessadoDTO2 = new ItemProcessadoDTO();
        itemProcessadoDTO2.setValorTotal(new BigDecimal("300.00"));
        itemProcessadoDTO2.setValorUnitario(new BigDecimal("100.00"));
        itemProcessadoDTO2.setQuantidade(3);
        itemProcessadoDTO2.setNomeProduto(null);

        List<ItemProcessadoDTO> listaEsperada = List.of(itemProcessadoDTO, itemProcessadoDTO2);

        List<ItemProcessadoDTO> listaProcessada = caixaService.processarItensDoPedido(carrinho);
        Assertions.assertEquals(listaProcessada.size(), listaEsperada.size());
        Assertions.assertTrue(listaProcessada.containsAll(listaProcessada));
        Assertions.assertTrue(listaEsperada.containsAll(listaProcessada));
    }

    @Test
    public void dadoListaDeItensProcessados_CalcularTotalDaCompraComSucesso() {
        ItemProcessadoDTO itemProcessadoDTO = new ItemProcessadoDTO();
        itemProcessadoDTO.setValorTotal(new BigDecimal("20.00"));
        ItemProcessadoDTO itemProcessadoDTO2 = new ItemProcessadoDTO();
        itemProcessadoDTO2.setValorTotal(new BigDecimal("100.00"));
        List<ItemProcessadoDTO> itemProcessadoDTOLista = List.of(itemProcessadoDTO, itemProcessadoDTO2);

        Assertions.assertEquals(new BigDecimal("120.00"), caixaService.calcularTotalCompra(itemProcessadoDTOLista));
    }

    @Test
    public void dadoCarrinhoNulo_GerarException() {
        Assertions.assertThrowsExactly(CarrinhoInvalidException.class, () -> {
            caixaService.processarItensDoPedido(null);
        });
    }

    @Test
    public void dadoPedidoVazio_GerarException() {
        Carrinho carrinho1 = new Carrinho(List.of());
        carrinho1.setPedido(new HashSet<>());
        Assertions.assertThrowsExactly(CarrinhoInvalidException.class, () -> {
            caixaService.processarItensDoPedido(carrinho1);
        });
    }
}

class CarrinhoMock extends Carrinho {

    public CarrinhoMock(List<ValidacaoItemDoPedido> validacoes) {
        super(validacoes);
    }
}

class ProdutoCrudMock extends ProdutoCrud {

    private ProdutoDTO produtoDTO = new ProdutoDTO();

    public ProdutoCrudMock() {
        super(null);
    }

    public void setPrecoProdutoDTO(BigDecimal preco) {
        produtoDTO.setPreco(preco);
    }

    @Override
    public ProdutoDTO read(int id) throws SQLException {
        return produtoDTO;
    }
}



