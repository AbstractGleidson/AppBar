package com.serverBar.serverBar.Request;

import com.serverBar.serverBar.Request.ItemRequest.ItemMoreRevenueRequest;
import com.serverBar.serverBar.Request.ItemRequest.ItemMoreSaleRequest;
import com.serverBar.serverBar.Request.PayRequest.PayRevenueRequest;
import com.serverBar.serverBar.models.Item;

import java.util.ArrayList;

public class BarResumeRequest {
    private ArrayList<ItemMoreSaleRequest> itemsMoreSale;
    private ArrayList<ItemMoreRevenueRequest> itemsMoreRevenue;
    private PayRevenueRequest intervalRevenue;

    public PayRevenueRequest getIntervalRevenue() {
        return intervalRevenue;
    }

    public ArrayList<ItemMoreRevenueRequest> getItemsMoreRevenue() {
        return itemsMoreRevenue;
    }

    public ArrayList<ItemMoreSaleRequest> getItemsMoreSale() {
        return itemsMoreSale;
    }

    public void setItemsMoreSale(ArrayList<ItemMoreSaleRequest> itemsMoreSale) {
        this.itemsMoreSale = itemsMoreSale;
    }

    public void setItemsMoreRevenue(ArrayList<ItemMoreRevenueRequest> itemsMoreRevenue) {
        this.itemsMoreRevenue = itemsMoreRevenue;
    }

    public void setIntervalRevenue(PayRevenueRequest intervalRevenue) {
        this.intervalRevenue = intervalRevenue;
    }
}
