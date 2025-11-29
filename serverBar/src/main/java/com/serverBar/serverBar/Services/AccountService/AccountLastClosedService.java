package com.serverBar.serverBar.Services.AccountService;

import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountLastClosedService {
    @Autowired
    private AccountInterface accountDAO;

    // Retorna a ultima conta fechada
    public Account accountLastClosed(String cpf)
    {
        ArrayList<Account> accounts = accountDAO.findByClientCpf(cpf);

        if (accounts == null || accounts.isEmpty()) {
            return null;
        }

        Account lastAccount = null;

        for (Account account : accounts) {
            if (!account.isOpen()) { // só contas fechadas
                if (lastAccount == null || account.getDate_close().isAfter(lastAccount.getDate_close())) {
                    lastAccount = account; // atualiza para a mais recente
                }
            }
        }

        return lastAccount;
    }
}
