package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ContaInterface;
import com.serverBar.serverBar.DAOs.PagamentoInterface;
import com.serverBar.serverBar.Request.PayRequest;
import com.serverBar.serverBar.models.Conta;
import com.serverBar.serverBar.models.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class PagamentoController {

    @Autowired
    private PagamentoInterface payDAO;
    @Autowired
    private ContaInterface accountDAO;

    @GetMapping("/payments")
    public ArrayList<Pagamento> getPayments()
    {
        return (ArrayList<Pagamento>) payDAO.findAll();
    }

    @GetMapping("/pay/id/{id}")
    public Optional<Pagamento> getPay(@PathVariable int id)
    {
        return payDAO.findById(id);
    }

    @GetMapping("/pay/account/{id}")
    public ArrayList<Pagamento> getPaymentsByAccount(@PathVariable int id)
    {
        return payDAO.findByContaId(id);
    }

    @GetMapping("/pay/{author}")
    public ArrayList<Pagamento> getPaymentsByAuthor(@PathVariable String author)
    {
        return payDAO.findByAutor(author);
    }

    @PostMapping("/pay")
    public ResponseEntity<?> postPay(@RequestBody PayRequest payRequest)
    {
        // Search account
        Optional<Conta> account  = accountDAO.findById(payRequest.getConta_id());

        // Account not exists in database
        if(account.isEmpty())
            return ResponseEntity.status(404).body("Conta não encontrada");

        // Account exists
        Conta newAccount = account.get();

        // Created pay
        Pagamento pay = new Pagamento();
        pay.setId(0);
        pay.setConta(newAccount);
        pay.setAutor(payRequest.getAutor());
        pay.setValor(payRequest.getValor());

        // Save pay and return response
        return ResponseEntity.status(200).body(payDAO.save(pay));
    }

    @PutMapping("/pay")
    public Pagamento updatePay(@RequestBody Pagamento pay)
    {
        return payDAO.save(pay);
    }

    @DeleteMapping("/payments")
    public void deleteAllPayments()
    {
        payDAO.deleteAll();
    }

    @DeleteMapping("/pay/{id}")
    public void deletePay(@PathVariable int id)
    {
        payDAO.deleteById(id);
    }
}
