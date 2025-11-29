package com.serverBar.serverBar.Request.ItemRequest;

public class ItemMoreSaleRequest {
    private int item_id;
    private String name;
    private int quantity;

    public String getName() {
        return name;
    }

    public int getItem_id() {
        return item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
