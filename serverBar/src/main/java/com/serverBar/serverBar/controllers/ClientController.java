package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ClientInterface;
import com.serverBar.serverBar.DAOs.ConsumptionInterface;
import com.serverBar.serverBar.DAOs.ItemInterface;
import com.serverBar.serverBar.Request.ClientRequest.ClientResumeRequest;
import com.serverBar.serverBar.Request.TipRequest.TipValuesRequest;
import com.serverBar.serverBar.Services.AccountCalculationConsumptionsService;
import com.serverBar.serverBar.Services.AccountCalculationValueService;
import com.serverBar.serverBar.Services.OpenAccountService;
import com.serverBar.serverBar.Services.TipCalculationService;
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
    private OpenAccountService openAccountService;

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
    public ResponseEntity<?> searchClientCpf(@PathVariable("cpf") int cpf) // Search client by cpf
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
    public void deleteClient(@PathVariable("cpf") int cpf) // Delete client for cpf
    {
        clientDAO.deleteById(cpf);
    }

    // Faz um resumo da conta de um cliente
    @GetMapping("/client/resume/{cpf}")
    public ResponseEntity<?> getResume(@PathVariable int cpf) throws IOException {

        Account account = openAccountService.getAccountOpen(cpf);
        if(account == null)
            return ResponseEntity.status(500).body("Cliente não possui uma conta aberta");

        int accountId = account.getAccountId();
        ClientResumeRequest clientResumeRequest = new ClientResumeRequest();


        Item covert = itemDAO.findById(0).orElse(null);
        double covertValue = 0;
        if(covert != null)
            covertValue = covert.getValue();

        TipValuesRequest tips = tipCalculationService.tipCalculation(accountId);
        double accountValue = accountCalculationConsumptionsService.accountCalculationConsumptions(accountId);
        double consumptionValue = accountValue - tips.getTipFullValue();

        clientResumeRequest.setConsumptions(consumptionDAO.findByAccountId(accountId));
        clientResumeRequest.setAccountValue(accountValue);
        clientResumeRequest.setCovert(covertValue);
        clientResumeRequest.setTipFull(tips.getTipFullValue());
        clientResumeRequest.setTipDrink(tips.getTipDrinkValue());
        clientResumeRequest.setTipFood(tips.getTipFoodValue());
        clientResumeRequest.setConsumptionsValue(consumptionValue);

        return ResponseEntity.ok().body(clientResumeRequest);
    }
}