package com.serverBar.serverBar.Services.PayService;

import com.serverBar.serverBar.DAOs.PayInterface;
import com.serverBar.serverBar.models.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PaymentFullAccountService {

    @Autowired
    private PayInterface payDAO;

    // Calcula o valor dos pagamentos ja realizados
    public double paymentFullAccountServe(int accountId)
    {
        ArrayList<Pay> paymentsAccount = payDAO.findByAccountId(accountId);

        double payFull = 0.0;

        for(Pay pay: paymentsAccount)
            payFull += pay.getValue();

        return payFull;
    }

}
