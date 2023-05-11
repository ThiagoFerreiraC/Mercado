package br.com.sinqia.dtos;

public class EstoqueDTO {
    private int produtoId;
    private int quantidadeEmEstoque;
    private int quantidadeDoPedido;

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public int getQuantidadeDoPedido() {
        return quantidadeDoPedido;
    }

    public void setQuantidadeDoPedido(int quantidadeDoPedido) {
        this.quantidadeDoPedido = quantidadeDoPedido;
    }
}
