package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ContaInterface;
import com.serverBar.serverBar.models.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ContaController {

    @Autowired
    private ContaInterface DAO; // Automatically generates DAOs methods

    @GetMapping("/accounts")
    public ArrayList<Conta> getAccounts() // Recover all database accounts
    {
        // Return List of accounts or Empty List
        return (ArrayList<Conta>) DAO.findAll();
    }

    @GetMapping("/account/{id}")
    public Optional<Conta> searchAccount(@PathVariable int id) // Search account by id
    {
        // Return Account or Null
        return DAO.findById(id);
    }

    @PostMapping("/account")
    public Conta postAccount(@RequestBody Conta account) // Save one new account
    {
        return DAO.save(account); // return new account
    }

    @PutMapping("/account")
    public Conta updateAccount(@RequestBody Conta account) // Updates a customer information
    {
        return DAO.save(account); // return update account
    }

    @DeleteMapping("/accounts")
    public void deleteAllAccounts() // Delete All account on database
    {
        DAO.deleteAll();
    }

    @DeleteMapping("/account/{id}")
    public void deleteAccount(@PathVariable int id) // Delete account by id
    {
        DAO.deleteById(id);
    }
}
