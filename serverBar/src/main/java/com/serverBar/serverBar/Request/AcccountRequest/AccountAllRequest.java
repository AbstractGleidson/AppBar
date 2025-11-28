package com.serverBar.serverBar.Request.AcccountRequest;

public class AccountAllRequest {
    private int id;
    private int cpf;
    private int pessoas;
    private boolean couvert;
    private boolean open;

    public int getId() {
        return id;
    }

    public int getCpf() {
        return cpf;
    }

    public int getPessoas() {
        return pessoas;
    }

    public boolean isCouvert() {
        return couvert;
    }

    public boolean isOpen() {
        return open;
    }

    public void setCouvert(boolean couvert) {
        this.couvert = couvert;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setPessoas(int pessoas) {
        this.pessoas = pessoas;
    }
}
