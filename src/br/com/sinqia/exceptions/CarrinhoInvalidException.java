package br.com.sinqia.exceptions;

public class CarrinhoInvalidException extends RuntimeException {
    public CarrinhoInvalidException(String message) {
        super(message);
    }
}
