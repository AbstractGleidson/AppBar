package com.serverBar.serverBar.Services;

import com.serverBar.serverBar.DAOs.ConsumptionInterface;
import com.serverBar.serverBar.Request.TipRequest;
import com.serverBar.serverBar.models.Consumption;
import com.serverBar.serverBar.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class TipCalculationService {

    @Autowired
    private ConsumptionInterface consumptionDAO;

    public Double tipCalculation(int accountId) throws IOException {
        TipService tipService = new TipService();
        TipRequest tips = tipService.loadTipsPercents();
        ArrayList<Consumption> consumptions = consumptionDAO.findByAccountId(accountId);

        double tip = 0.0;

        for(Consumption consumption: consumptions)
        {
            Item item = consumption.getItem();
            switch (consumption.getItem().getType())
            {
                case 2:
                    tip += (tips.getTipPercentDrink() / 100) * (consumption.getQuantity() * item.getValue());
                    break;
                case 3:
                    tip += (tips.getTipPercentFood() / 100) * (consumption.getQuantity() * item.getValue());
                    break;
                default:
                    break;
            }
        }

        return tip;
    }
}
