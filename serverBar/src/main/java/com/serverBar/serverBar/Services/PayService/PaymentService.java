package com.serverBar.serverBar.Services.PayService;

import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.DAOs.ConsumptionInterface;
import com.serverBar.serverBar.Services.AccountService.AccountCalculationConsumptionsService;
import com.serverBar.serverBar.models.Account;
import com.serverBar.serverBar.models.Consumption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class PaymentService {

    @Autowired
    private AccountInterface accountDAO;
    @Autowired
    private ConsumptionInterface consumptionDAO;
    @Autowired
    AccountCalculationConsumptionsService accountCalculationValueService;
    @Autowired
    PaymentFullAccountService paymentFullAccountService;

    public boolean validatedPayment(int accountId, double payValue) throws IOException {
        Account account = accountDAO.findById(accountId).orElse(null);

        if (account == null)
            return false;


        double valueAccount = account.getValue();


        double payAccount = paymentFullAccountService.paymentFullAccountServe(accountId);

        return (valueAccount - payAccount >= 0);
    }
}
