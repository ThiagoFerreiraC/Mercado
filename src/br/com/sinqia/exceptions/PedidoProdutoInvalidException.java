package br.com.sinqia.exceptions;

public class PedidoProdutoInvalidException extends RuntimeException {
    public PedidoProdutoInvalidException(String message) {
        super(message);
    }
}
