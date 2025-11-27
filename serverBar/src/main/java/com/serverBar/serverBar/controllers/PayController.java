package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.DAOs.PayInterface;
import com.serverBar.serverBar.Request.PayRequest.PayPostRequest;
import com.serverBar.serverBar.Request.PayRequest.PayPutRequest;
import com.serverBar.serverBar.Request.PayRequest.PayRevenueRequest;
import com.serverBar.serverBar.Services.PaymentFullAccountService;
import com.serverBar.serverBar.Services.PaymentService;
import com.serverBar.serverBar.models.Account;
import com.serverBar.serverBar.models.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class PayController {

    @Autowired // Automatically generates DAOs methods
    private PayInterface payDAO;
    @Autowired
    private AccountInterface accountDAO;
    @Autowired
    private PaymentService paymentService;

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
    public ResponseEntity<?> postPay(@RequestBody PayPostRequest payRequest) throws IOException {
        if(paymentService.validatedPayment(payRequest.getConta_id(), payRequest.getValor()))
            return ResponseEntity.status(500).body("Não foi possivel realizar o pagamento, pois o valor é maior que a conta.");

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

    @PutMapping("/pay")
    public ResponseEntity<?> updatePay(@RequestBody PayPutRequest request) throws IOException {
        Pay pay = payDAO.findById(request.getPay_id()).orElse(null);

        if (pay == null)
            return ResponseEntity.status(404).body("Não foi possivel atualizar o pagamento, pois ele não existe!");

        if(request.getConta_id() != null) {
            // Check if account exists
            Account account = accountDAO.findById(request.getConta_id()).orElse(null);

            // Account not exists in database
            if (account == null)
                return ResponseEntity.status(404).body("Conta não encontrada");

            pay.setAccount(account);
        }

        if(request.getAutor() != null)
            pay.setAuthor(request.getAutor());

        if(request.getValor() != null) {
            if (request.getValor() > pay.getValue()) {
                if (paymentService.validatedPayment(
                        request.getConta_id() == null ? pay.getAccount().getAccountId() : request.getConta_id(),
                        request.getValor() - pay.getValue()
                    )
                )
                    pay.setValue(request.getValor());
            }
            else
                pay.setValue(request.getValor());
        }



        // Save pay and return server response
        return ResponseEntity.ok().body(payDAO.save(pay));
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

    @GetMapping("/pay/interval")
    public ResponseEntity<?> getBarRecipe(@RequestParam String startDate, @RequestParam String endDate)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatacao da data

        LocalDate start = LocalDate.parse(startDate, format);
        LocalDate end = LocalDate.parse(endDate, format);

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(23, 59, 59);

        ArrayList<Pay> payments = payDAO.findAllByDateBetween(startDateTime, endDateTime);

        PayRevenueRequest request = new PayRevenueRequest();

        for(Pay pay: payments)
             request.revenueIncrement(pay.getValue());

        request.setAmountPayments(payments.size());

        return ResponseEntity.ok(request);
    }
}
