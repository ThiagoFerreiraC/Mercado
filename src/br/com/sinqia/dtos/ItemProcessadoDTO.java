package br.com.sinqia.dtos;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemProcessadoDTO {

    private String nomeProduto;
    private int quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemProcessadoDTO that = (ItemProcessadoDTO) o;
        return quantidade == that.quantidade && Objects.equals(nomeProduto, that.nomeProduto) && Objects.equals(valorUnitario, that.valorUnitario) && Objects.equals(valorTotal, that.valorTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeProduto, quantidade, valorUnitario, valorTotal);
    }
}
