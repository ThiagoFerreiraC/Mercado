package br.com.sinqia.dtos;

public class EstoquePorCategoriaDTO implements Comparable<EstoquePorCategoriaDTO> {

    private String categoria;
    private int quantidade;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int compareTo(EstoquePorCategoriaDTO o) {
        return this.getCategoria().compareTo(o.getCategoria());
    }
}
