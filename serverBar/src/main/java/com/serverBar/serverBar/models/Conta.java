package com.serverBar.serverBar.models;

import jakarta.persistence.*;

@Entity
@Table(name = "conta") // Define a relationship between the account class and the account table
public class Conta {

    @Id // Defines this field as a primary key
    @Column(name = "idConta", nullable = false) // Relates this attribute to the database column
    private int idConta;

    @OneToOne // Define a relationship one to one
    @JoinColumn(name = "Cliente_CPF", nullable = false) // Relates this attribute to the database column
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
        return idConta;
    }
}
