package com.serverBar.serverBar.Request.AcccountRequest;

public class RequestOpenAccount {
    private int cpf_client;
    private int peoples;
    private boolean couvert;

    public int getPeoples() {
        return peoples;
    }

    public int getCpf_client() {
        return cpf_client;
    }

    public boolean isCouvert(){
        return couvert;
    }

    public void setPeoples(int peoples) {
        this.peoples = peoples;
    }

    public void setCouvert(boolean couvert) {
        this.couvert = couvert;
    }

    public void setCpf_client(int cpf_client) {
        this.cpf_client = cpf_client;
    }
}
