package com.serverBar.serverBar.Services;

import com.serverBar.serverBar.Request.TipRequest;
import com.serverBar.serverBar.models.Consumption;
import com.serverBar.serverBar.models.Item;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class AccountCalculationValue {

    public Double accountCalculation(ArrayList<Consumption> consumptions) throws IOException {
        TipService tipService = new TipService();
        TipRequest tips = tipService.loadTipsPercents();

        double accountValue = 0.0;

        for(Consumption consumption: consumptions)
        {
            Item item = consumption.getItem();
            switch (consumption.getItem().getType())
            {
                case 2:
                    accountValue += consumption.getQuantity() * item.getValue();
                    accountValue += (tips.getTipPercentDrink() / 100) * (consumption.getQuantity() * item.getValue());
                    break;
                case 3:
                    accountValue += consumption.getQuantity() * item.getValue();
                    accountValue += (tips.getTipPercentFood() / 100) * (consumption.getQuantity() * item.getValue());
                    break;
                default:
                    accountValue += consumption.getQuantity() * item.getValue();
                    break;
            }
        }

        return accountValue;
    }
}
