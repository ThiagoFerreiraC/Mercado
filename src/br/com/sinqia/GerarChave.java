package br.com.sinqia;

import java.util.UUID;

public class GerarChave {

    public String gerarChave() {
        return UUID.randomUUID().toString();
    }
}
