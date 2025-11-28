package com.serverBar.serverBar.Services;

import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OpenAccountService {

    @Autowired
    private AccountInterface accountDAO;

    // Acha a primeira conta aberta do cliente
    public Account getAccountOpen(int cpf)
    {
        ArrayList<Account> accounts = accountDAO.findByClientCpf(cpf);

        for(Account account: accounts)
        {
            if(account.isOpen())
                return account;
        }

        return null;
    }

}
