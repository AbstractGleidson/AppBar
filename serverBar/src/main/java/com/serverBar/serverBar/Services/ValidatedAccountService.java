package com.serverBar.serverBar.Services;

import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.DAOs.ClientInterface;
import com.serverBar.serverBar.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ValidatedAccountService {

    @Autowired
    private AccountInterface accountDAO;

    @Autowired
    private ClientInterface clientDAO;

    // Verifica se o cliente ja tem alguma conta aberta
    public boolean validateOpenAccount(int cpf)
    {
        ArrayList<Account> accountsClient = accountDAO.findByClientCpf(cpf);

        for (Account account: accountsClient)
            if(account.isOpen())
                return false;

        return true;
    }
}
