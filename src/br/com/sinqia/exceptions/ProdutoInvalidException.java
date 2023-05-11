package br.com.sinqia.exceptions;

public class ProdutoInvalidException extends RuntimeException{
    public ProdutoInvalidException(String message) {
        super(message);
    }
}
