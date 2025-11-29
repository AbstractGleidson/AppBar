package com.serverBar.serverBar.Services.AccountService;

import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.DAOs.ConsumptionInterface;
import com.serverBar.serverBar.Services.PayService.PaymentFullAccountService;
import com.serverBar.serverBar.models.Account;
import com.serverBar.serverBar.models.Consumption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class AccountCalculationValueService {
    @Autowired
    private AccountInterface accountDAO;
    @Autowired
    private ConsumptionInterface consumptionDAO;
    @Autowired
    AccountCalculationConsumptionsService accountCalculationValueService;
    @Autowired
    PaymentFullAccountService paymentFullAccountService;


    public Double accountCalculation(int accountId) throws IOException {
        Account account = accountDAO.findById(accountId).orElse(null);

        ArrayList<Consumption> accountConsumptions = consumptionDAO.findByAccountId(accountId);
        double valueAccount =  accountCalculationValueService.accountCalculationConsumptions(accountId);
        double payAccount = paymentFullAccountService.paymentFullAccountServe(accountId);

        return valueAccount - payAccount;
    }
}
