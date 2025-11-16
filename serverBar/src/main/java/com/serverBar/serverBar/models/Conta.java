package com.serverBar.serverBar.models;

import jakarta.persistence.*;

@Entity
@Table(name = "conta") // Define a relationship between the account class and the account table
public class Conta {

    @Id // Defines this field as a primary key
    @Column(name = "id", nullable = false) // Relates this attribute to the database column
    private int id;

    @OneToOne // Define a relationship one to one
    @JoinColumn(name = "cliente_cpf", nullable = false) // Relates this attribute to the database column
    private Cliente cliente;

    @Column(name = "gorjeta", nullable = true) // Relates this attribute to the database column
    private Double gorjeta;

    public Cliente getCliente() {
        return cliente;
    }

    public Double getGorjeta() {
        return gorjeta;
    }

    public int getIdConta() {
        return id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setGorjeta(Double gorjeta) {
        this.gorjeta = gorjeta;
    }

    public void setIdConta(int idConta) {
        this.id = idConta;
    }
}
