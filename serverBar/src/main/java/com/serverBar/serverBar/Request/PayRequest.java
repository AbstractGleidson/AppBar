package com.serverBar.serverBar.Request;

public class PayRequest {
    private int conta_id;
    private Double valor;
    private String autor;

    public Double getValor() {
        return valor;
    }

    public String getAutor() {
        return autor;
    }

    public int getConta_id() {
        return conta_id;
    }
}
