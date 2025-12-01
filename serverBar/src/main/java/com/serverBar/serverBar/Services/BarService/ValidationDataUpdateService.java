package com.serverBar.serverBar.Services.BarService;

import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ValidationDataUpdateService {
    @Autowired
    private AccountInterface accountDAO;

    // Verifica se existe alguma conta aberta
    public boolean validationDataUpdate()
    {
        ArrayList<Account> accounts = (ArrayList<Account>) accountDAO.findAll();

        for(Account account: accounts)
            if(account.isOpen())
                return false;

        return true;
    }

}
