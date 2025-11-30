package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ClientInterface;
import com.serverBar.serverBar.DAOs.ConsumptionInterface;
import com.serverBar.serverBar.DAOs.ItemInterface;
import com.serverBar.serverBar.Request.ClientRequest.ClientResumeRequest;
import com.serverBar.serverBar.Request.TipRequest.TipValuesRequest;
import com.serverBar.serverBar.Services.AccountService.AccountCalculationConsumptionsService;
import com.serverBar.serverBar.Services.AccountService.AccountCalculationValueService;
import com.serverBar.serverBar.Services.AccountService.AccountLastClosedService;
import com.serverBar.serverBar.Services.AccountService.AccountOpenService;
import com.serverBar.serverBar.Services.PayService.PaymentFullAccountService;
import com.serverBar.serverBar.Services.PayService.PaymentService;
import com.serverBar.serverBar.Services.TipService.TipCalculationService;
import com.serverBar.serverBar.models.Account;
import com.serverBar.serverBar.models.Client;
import com.serverBar.serverBar.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class ClientController {

    @Autowired
    private ClientInterface clientDAO; // Automatically generates DAOs methods
    @Autowired
    private ConsumptionInterface consumptionDAO;
    @Autowired
    private AccountCalculationConsumptionsService accountCalculationConsumptionsService;
    @Autowired
    private AccountCalculationValueService accountCalculationValueService;
    @Autowired
    private TipCalculationService tipCalculationService;
    @Autowired
    private ItemInterface itemDAO;
    @Autowired
    private AccountOpenService openAccountService;
    @Autowired
    private AccountLastClosedService accountLastClosedService;
    @Autowired
    private PaymentFullAccountService paymentFullAccountService;

    @GetMapping("/clients")
    public ArrayList<Client> getClients() // Recover all database clients
    {
        // Return List of Clients or Empty List
        return (ArrayList<Client>) clientDAO.findAll();
    }

    @PostMapping("/client")
    public ResponseEntity<?> postClient(@RequestBody Client client) // Save one new client
    {
        return ResponseEntity.ok(clientDAO.save(client)); // Return new client
    }

    @PutMapping("/client")
    public Client updateClient(@RequestBody Client client) // Updates a customer information
    {
        return clientDAO.save(client); // Return update client
    }

    @GetMapping("/client/{cpf}")
    public ResponseEntity<?> searchClientCpf(@PathVariable("cpf") String cpf) // Search client by cpf
    {
        Client client = clientDAO.findById(cpf).orElse(null);

        if(client == null)
            return ResponseEntity.status(500).body("O cliente não existe");

        // Client or Null
        return ResponseEntity.ok(clientDAO.findById(cpf)); // Return the client if it exists in the database
    }

    @DeleteMapping("/clients")
    public void deleteAllClients() // Delete all clients on database
    {
        clientDAO.deleteAll();
    }

    @DeleteMapping("/client/{cpf}")
    public void deleteClient(@PathVariable("cpf") String cpf) // Delete client for cpf
    {
        clientDAO.deleteById(cpf);
    }

    // Faz um resumo da conta de um cliente
    @GetMapping("/client/resume/{cpf}")
    public ResponseEntity<?> getResume(@PathVariable String cpf) throws IOException {

        // tenta achar um conta aberta
        Account account = openAccountService.getAccountOpen(cpf);

        // Procura a ultima conta que foi fechada
        if(account == null)
        {
            account = accountLastClosedService.accountLastClosed(cpf);
            if(account == null)
                return ResponseEntity.status(500).body("Cliente não possui nenhuma conta!");
        }


        int accountId = account.getAccountId();
        ClientResumeRequest clientResumeRequest = new ClientResumeRequest();


        Item covert = itemDAO.findById(0).orElse(null);
        double covertValue = 0;
        if(covert != null)
            covertValue = covert.getValue();

        double consumptionValue = 0.0;
        double accountValue = 0.0;
        TipValuesRequest tips;
        double tempValue;

        if(account.isOpen()) {
            tips = tipCalculationService.tipCalculation(accountId);
            tempValue = accountCalculationConsumptionsService.accountCalculationConsumptions(accountId);
        }else
        {
            tips = new TipValuesRequest();

            tips.setTipFoodValue(account.getTipFood());
            tips.setTipDrinkValue(account.getTipDrink());
            tips.setTipFoodValue(account.getTipFood() + account.getTipDrink());
            tempValue = account.getValue();
        }
        accountValue = tempValue - paymentFullAccountService.paymentFullAccountServe(accountId);

        consumptionValue = accountValue - tips.getTipFullValue();

        clientResumeRequest.setConsumptions(consumptionDAO.findByAccountId(accountId));
        clientResumeRequest.setAccountValue(accountValue <= 0 ? 0 : accountValue);
        clientResumeRequest.setCovert(covertValue);
        clientResumeRequest.setTipFull(tips.getTipFullValue());
        clientResumeRequest.setTipDrink(tips.getTipDrinkValue());
        clientResumeRequest.setTipFood(tips.getTipFoodValue());
        clientResumeRequest.setConsumptionsValue(consumptionValue);

        return ResponseEntity.ok().body(clientResumeRequest);
    }
}