package com.serverBar.serverBar.Request.AcccountRequest;

// Body for request of post Account
public class AccountPostRequest {
    private int cliente_cpf;
    private boolean open;
    private int peoples;

    public int getCliente_cpf() {
        return cliente_cpf;
    }

    public boolean getOpen() {
        return open;
    }

    public int getPeoples() {
        return peoples;
    }
}
