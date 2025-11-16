package com.serverBar.serverBar.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @Column(name = "id")
    private int id = 0; // Id is Auto Increment

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = true)
    private Conta conta;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "autor", length = 100, nullable = true)
    private String autor;

    // insertable = false, indicates that the field should not be filled, as it will be filled in the database
    // updatable = false, indicates that the field should not be update
    @Column(name = "data", nullable = false, insertable = false, updatable = false)
    private LocalDateTime data;

    public Conta getConta() {
        return conta;
    }

    public int getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getAutor() {
        return autor;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
