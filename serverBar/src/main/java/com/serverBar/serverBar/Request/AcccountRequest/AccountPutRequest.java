package com.serverBar.serverBar.Request.AcccountRequest;

public class AccountPutRequest {
    private Integer cliente_cpf;
    private int account_id;
    private Boolean open;
    private int peoples;

    public Integer getCliente_cpf() {
        return cliente_cpf;
    }

    public Boolean getOpen() {
        return open;
    }

    public int getAccount_id() {
        return account_id;
    }

    public int getPeoples() {
        return peoples;
    }
}
