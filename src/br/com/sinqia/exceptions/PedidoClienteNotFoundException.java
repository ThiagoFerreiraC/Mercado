package br.com.sinqia.exceptions;

public class PedidoClienteNotFoundException extends RuntimeException {
    public PedidoClienteNotFoundException(String message) {
        super(message);
    }
}
