package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ClientInterface;
import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.Request.AccountRequest;
import com.serverBar.serverBar.models.Client;
import com.serverBar.serverBar.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountInterface accountDAO;// Automatically generates DAOs methods
    @Autowired
    private ClientInterface clientDAO;

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
    public ResponseEntity<?> postAccount(@RequestBody AccountRequest accountRequest) // Save one new account
    {
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
        account.setTip(null);

        // Save account and return serve response
        return ResponseEntity.status(200).body(accountDAO.save(account));
    }

    // ======================================================================
    // You need to find this method, it is not updating the values just
    // by creating a new account
    // ======================================================================
    @PutMapping("/account")
    public ResponseEntity<?> updateAccount(@RequestBody AccountRequest accountRequest) // Updates a customer information
    {
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
        account.setTip(null);

        // Save account and return serve response
        return ResponseEntity.status(200).body(accountDAO.save(account));
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
}