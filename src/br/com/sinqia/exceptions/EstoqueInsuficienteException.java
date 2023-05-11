package br.com.sinqia.exceptions;

public class EstoqueInsuficienteException extends RuntimeException{

    public EstoqueInsuficienteException(String message) {
        super(message);
    }
}
