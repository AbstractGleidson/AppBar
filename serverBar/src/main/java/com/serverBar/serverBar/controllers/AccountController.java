package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ClientInterface;
import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.DAOs.ConsumptionInterface;
import com.serverBar.serverBar.Request.AcccountRequest.AccountPostRequest;
import com.serverBar.serverBar.Request.AcccountRequest.AccountPutRequest;
import com.serverBar.serverBar.Services.AccountCalculationConsumptionsService;
import com.serverBar.serverBar.Services.AccountCalculationValueService;
import com.serverBar.serverBar.Services.TipCalculationService;
import com.serverBar.serverBar.Services.ValidatedAccountService;
import com.serverBar.serverBar.models.Client;
import com.serverBar.serverBar.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountInterface accountDAO;// Automatically generates DAOs methods
    @Autowired
    private ClientInterface clientDAO;
    @Autowired
    private AccountCalculationConsumptionsService accountCalculationConsumptionsService;
    @Autowired
    private TipCalculationService tipCalculationService;
    @Autowired
    private ConsumptionInterface consumptionDAO;
    @Autowired
    private ValidatedAccountService validatedAccountService;
    @Autowired
    private AccountCalculationValueService accountCalculationValueService;

    @GetMapping("/accounts")
    public ArrayList<Account> getAccounts() // Recover all database accounts
    {
        // Return List of accounts or Empty List
        return (ArrayList<Account>) accountDAO.findAll();
    }

    @GetMapping("/account/{id}")
    public Optional<Account> searchAccount(@PathVariable int id) // Search account by id
    {
        // Return Account or Null
        return accountDAO.findById(id);
    }

    @PostMapping("/account")
    public ResponseEntity<?> postAccount(@RequestBody AccountPostRequest accountRequest) // Save one new account
    {
        if(!validatedAccountService.validateOpenAccount(accountRequest.getCliente_cpf()))
            return ResponseEntity.status(500).body("O cliente já tem uma conta aberta!");

        // Check if client exists
        Optional<Client> client = clientDAO.findById(accountRequest.getCliente_cpf());

        // client not exists
        if(client.isEmpty())
            return ResponseEntity.status(404).body("Não foi possível achar o cliente");

        // get client
        Client newClient = client.get();

        // Construct account
        Account account = new Account();
        account.setAccountId(0); // id is auto generated
        account.setClient(newClient);
        account.setOpen(accountRequest.getOpen());
        account.setTip(null);

        // Save account and return serve response
        return ResponseEntity.ok().body(accountDAO.save(account));
    }

    @PutMapping("/account")
    public ResponseEntity<?> updateAccount(@RequestBody AccountPutRequest request)
    {
        Account account = accountDAO.findById(request.getAccount_id()).orElse(null);

        if (account == null) {
            return ResponseEntity.status(404).body("Conta não existe!");
        }

        // Atualizar cliente se enviado
        if (request.getCliente_cpf() != null) {
            Client client = clientDAO.findById(request.getCliente_cpf()).orElse(null);

            if (client == null)
                return ResponseEntity.status(404).body("Cliente não encontrado!");

            account.setClient(client);
        }

        // Atualizar open (se vier no JSON)
        if (request.getOpen() != null) {
            account.setOpen(request.getOpen());
        }

        // Salvar atualizações
        accountDAO.save(account);

        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/accounts")
    public void deleteAllAccounts() // Delete All account on database
    {
        accountDAO.deleteAll();
    }

    @DeleteMapping("/account/{id}")
    public void deleteAccount(@PathVariable int id) // Delete account by id
    {
        accountDAO.deleteById(id);
    }

    @GetMapping("/account/tip/{id}")
    public ResponseEntity<?> getTip(@PathVariable int id) throws IOException {
        return ResponseEntity.ok().body(tipCalculationService.tipCalculation(consumptionDAO.findByAccountId(id)));
    }

    @GetMapping("/account/value/{id}")
    public ResponseEntity<?> getAccountValue(@PathVariable int id) throws IOException {
        return ResponseEntity.ok().body(accountCalculationValueService.accountCalculation(id));
    }

    @GetMapping("/accounts/{cpf}")
    public ArrayList<Account> getClientAccounts(@PathVariable int cpf)
    {
        return accountDAO.findByClientCpf(cpf);
    }

}