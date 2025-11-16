package com.serverBar.serverBar.Request;

// Body for request of post Consumption
public class ConsumptionRequest {
    private int quantidade;
    private int num_item;
    private int conta_id;

    public int getConta_id() {
        return conta_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getNum_item() {
        return num_item;
    }
}
