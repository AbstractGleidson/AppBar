package com.serverBar.serverBar.Request.ClientRequest;

import com.serverBar.serverBar.models.Consumption;

import java.util.ArrayList;

public class ClientResumeRequest {

    private ArrayList<Consumption> consumptions;
    private double consumptionsValue;
    private double tip;
    private double accountValue;
    private double covert;

    public void setAccountValue(double accountValue) {
        this.accountValue = accountValue;
    }

    public void setConsumptions(ArrayList<Consumption> consumptions) {
        this.consumptions = consumptions;
    }

    public void setConsumptionsValue(double consumptionsValue) {
        this.consumptionsValue = consumptionsValue;
    }

    public void setCovert(double covert) {
        this.covert = covert;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public ArrayList<Consumption> getConsumptions() {
        return consumptions;
    }

    public double getAccountValue() {
        return accountValue;
    }

    public double getConsumptionsValue() {
        return consumptionsValue;
    }

    public double getCovert() {
        return covert;
    }

    public double getTip() {
        return tip;
    }
}
