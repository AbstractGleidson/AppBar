package com.serverBar.serverBar.Request;

public class TipRequest {
    private Double tipPercentDrink;
    private Double tipPercentFood;

    public void setTipPercentDrink(Double tipPercentDrink) {
        this.tipPercentDrink = tipPercentDrink;
    }

    public void setTipPercentFood(Double tipPercentFood) {
        this.tipPercentFood = tipPercentFood;
    }

    public Double getTipPercentDrink() {
        return tipPercentDrink;
    }

    public Double getTipPercentFood() {
        return tipPercentFood;
    }
}
