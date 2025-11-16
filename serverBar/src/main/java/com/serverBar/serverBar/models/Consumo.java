package com.serverBar.serverBar.models;

import jakarta.persistence.*;

@Entity
@Table(name = "consumo") // Define a relationship between the consumption class and the consumption table
public class Consumo {

    @Id // define this field as a primary key
    @Column(name = "id") // Relates this attribute to the database column
    private int id;

    @Column(name = "quantidade", nullable = false) // Relates this attribute to the database column
    private int quantidade;

    @OneToOne // Define a relationship one to one
    @JoinColumn(name = "num_item", nullable = false) // Relates this attribute to the database column
    private Item item;

    @ManyToOne // Define a relationship many to one
    @JoinColumn(name = "conta_id", nullable = false) // Relates this attribute to the database column
    private Conta conta;

    public int getQuantidade() {
        return quantidade;
    }

    public Conta getConta() {
        return conta;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
