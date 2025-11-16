package com.serverBar.serverBar.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item") // Defines a relationship between the Item class and the Item table
public class Item {

    @Id // Defines this field as a primary key
    @Column(name = "numItem") // Relates this attribute to the database column
    private int numItem;

    @Column(name = "tipo", nullable = false) // Relates this attribute to the database column
    private int tipo;

    @Column(name = "valor", nullable = false) // Relates this attribute to the database column
    private double valor;

    @Column(name = "nome", length = 100, nullable = false) // Relate this attribute to the database column
    private String nome;

    public double getValor() {
        return valor;
    }

    public int getNumItem() {
        return numItem;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
