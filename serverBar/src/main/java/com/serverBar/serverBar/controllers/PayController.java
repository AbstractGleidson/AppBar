package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.DAOs.PayInterface;
import com.serverBar.serverBar.Request.PayRequest;
import com.serverBar.serverBar.models.Account;
import com.serverBar.serverBar.models.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class PayController {

    @Autowired // Automatically generates DAOs methods
    private PayInterface payDAO;
    @Autowired
    private AccountInterface accountDAO;

    @GetMapping("/payments") // Recover all database payments
    public ArrayList<Pay> getPayments()
    {
        // Return List of payments or Empty List
        return (ArrayList<Pay>) payDAO.findAll();
    }

    @GetMapping("/pay/id/{id}") // Search pay by id
    public Optional<Pay> getPay(@PathVariable int id)
    {
        // return pay or null
        return payDAO.findById(id);
    }

    @GetMapping("/pay/account/{id}") // Search payments by account id
    public ArrayList<Pay> getPaymentsByAccount(@PathVariable int id)
    {
        // Return List of payments or Empty List
        return payDAO.findByAccountId(id);
    }

    @GetMapping("/pay/{author}") // Search payments by author
    public ArrayList<Pay> getPaymentsByAuthor(@PathVariable String author)
    {
        // Return List of payments or Empty List
        return payDAO.findByAuthor(author);
    }

    @PostMapping("/pay") // Save one new pay on database
    public ResponseEntity<?> postPay(@RequestBody PayRequest payRequest)
    {
        // Check if account exists
        Optional<Account> account  = accountDAO.findById(payRequest.getConta_id());

        // Account not exists in database
        if(account.isEmpty())
            return ResponseEntity.status(404).body("Conta não encontrada");

        // get account
        Account newAccount = account.get();

        // Construct pay
        Pay pay = new Pay();
        pay.setId(0); // id is auto generated
        pay.setAccount(newAccount);
        pay.setAuthor(payRequest.getAutor());
        pay.setValue(payRequest.getValor());

        // Save pay and return server response
        return ResponseEntity.status(200).body(payDAO.save(pay));
    }

    // ======================================================================
    // You need to find this method, it is not updating the values just
    // by creating a new account
    // ======================================================================
    @PutMapping("/pay")
    public ResponseEntity<?> updatePay(@RequestBody PayRequest payRequest)
    {
        // Check if account exists
        Optional<Account> account  = accountDAO.findById(payRequest.getConta_id());

        // Account not exists in database
        if(account.isEmpty())
            return ResponseEntity.status(404).body("Conta não encontrada");

        // get account
        Account newAccount = account.get();

        // Construct pay
        Pay pay = new Pay();
        pay.setId(0); // id is auto generated
        pay.setAccount(newAccount);
        pay.setAuthor(payRequest.getAutor());
        pay.setValue(payRequest.getValor());

        // Save pay and return server response
        return ResponseEntity.status(200).body(payDAO.save(pay));
    }

    @DeleteMapping("/payments") // Delete all payments in database
    public void deleteAllPayments()
    {
        payDAO.deleteAll();
    }

    @DeleteMapping("/pay/{id}") // Delete pay in database by id
    public void deletePay(@PathVariable int id)
    {
        payDAO.deleteById(id);
    }
}
