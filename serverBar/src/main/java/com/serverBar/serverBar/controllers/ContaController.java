package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ClienteInterface;
import com.serverBar.serverBar.DAOs.ContaInterface;
import com.serverBar.serverBar.Request.AccountRequest;
import com.serverBar.serverBar.models.Cliente;
import com.serverBar.serverBar.models.Conta;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ContaController {

    @Autowired
    private ContaInterface accountDAO;// Automatically generates DAOs methods
    @Autowired
    private ClienteInterface clientDAO;

    @GetMapping("/accounts")
    public ArrayList<Conta> getAccounts() // Recover all database accounts
    {
        // Return List of accounts or Empty List
        return (ArrayList<Conta>) accountDAO.findAll();
    }

    @GetMapping("/account/{id}")
    public Optional<Conta> searchAccount(@PathVariable int id) // Search account by id
    {
        // Return Account or Null
        return accountDAO.findById(id);
    }

    @PostMapping("/account")
    public ResponseEntity<?> postAccount(@RequestBody AccountRequest accountRequest) // Save one new account
    {
        Optional<Cliente> client = clientDAO.findById(accountRequest.getCliente_cpf());

        if(client.isEmpty())
            ResponseEntity.status(404).body("Não foi possível achar o cliente");

        Cliente newClient = client.get();

        Conta account = new Conta();
        account.setIdConta(0);
        account.setCliente(newClient);
        account.setGorjeta(null);

        return ResponseEntity.status(200).body(accountDAO.save(account)); // return new account
    }

    @PutMapping("/account")
    public Conta updateAccount(@RequestBody Conta account) // Updates a customer information
    {
        return accountDAO.save(account); // return update account
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