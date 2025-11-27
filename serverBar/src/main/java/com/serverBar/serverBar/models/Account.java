package com.serverBar.serverBar.models;

import jakarta.persistence.*;

@Entity
@Table(name = "conta") // Define a relationship between the account class and the account table
public class Account {

    @Id // Defines this field as a primary key
    @Column(name = "id", nullable = false) // Relates this attribute to the database column
    private int id; // auto generated

    @OneToOne // Define a relationship one to one
    @JoinColumn(name = "cliente_cpf", nullable = false) // Relates this attribute to the database column
    private Client client;

    @Column(name = "gorjeta", nullable = true) // Relates this attribute to the database column
    private Double tip;

    @Column(name = "aberta", nullable = false)
    private boolean isOpen;

    public Client getClient() {
        return client;
    }

    public Double getTip() {
        return tip;
    }

    public int getAccountId() {
        return id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    public void setAccountId(int idConta) {
        this.id = idConta;
    }

    public int getId() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOpen(boolean open) {
        System.out.printf("Mesa aberta: %b", open);
        this.isOpen = open;
    }
}
