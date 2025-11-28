package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ConsumptionInterface;
import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.DAOs.ItemInterface;
import com.serverBar.serverBar.Request.ConsumptionRequest.ConsumptionPostRequest;
import com.serverBar.serverBar.Request.ConsumptionRequest.ConsumptionPutRequest;
import com.serverBar.serverBar.models.Consumption;
import com.serverBar.serverBar.models.Account;
import com.serverBar.serverBar.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ConsumptionController {

    @Autowired
    private ConsumptionInterface consumptionDAO; // Automatically generates DAOs methods
    @Autowired
    private AccountInterface accountDAO;
    @Autowired
    private ItemInterface itemDAO;

    @GetMapping("/consumptions")
    public ArrayList<Consumption> getConsumptions() // Recover all database consumptions
    {
        // Return List of consumptions or Empty List
        return (ArrayList<Consumption>) consumptionDAO.findAll();
    }

    @GetMapping("/consumption/{id}") // Search consumption by id
    public Optional<Consumption> searchConsumption(@PathVariable int id)
    {
        // Return consumption or Null
        return consumptionDAO.findById(id);
    }

    @GetMapping("/consumption/account/{id}") // Search consumption of an account
    public ArrayList<Consumption> searchConsumptionByAccount(@PathVariable int id)
    {
        // Return List of consumptions or Empty List
        return consumptionDAO.findByAccountId(id);
    }

    @GetMapping("consumption/account/client/{name}") // Search consumptions of client by name
    public ArrayList<Consumption> searchConsumptionsByAccountClientName(@PathVariable String name)
    {
        // Return List of consumptions or Empty List
        return consumptionDAO.findByAccountClientName(name);
    }

    @PostMapping("/consumption") // Save one new consumption
    public ResponseEntity<?> postConsumption(@RequestBody ConsumptionPostRequest consumptionRequest)
    {
        // Check if the account exists
        Optional<Account> account = accountDAO.findById(consumptionRequest.getConta_id());
        // Check if the item exists
        Optional<Item>  item = itemDAO.findById(consumptionRequest.getNum_item());

        // Account not exists
        if(account.isEmpty())
            return ResponseEntity.status(404).body("Conta não existe!");
        // Item not exists
        if(item.isEmpty())
            return ResponseEntity.status(404).body("Item não existe!");

        Account newAccount = account.get(); // get account
        Item newItem = item.get(); // get item

        // Constructs consumption
        Consumption consumption = new Consumption();
        consumption.setId(0); // id is auto generated
        consumption.setAccount(newAccount);
        consumption.setItem(newItem);
        consumption.setQuantity(consumptionRequest.getQuantidade());

        // return new consumption and server response
        return ResponseEntity.status(200).body(consumptionDAO.save(consumption));
    }

    @PutMapping("/consumption")
    public ResponseEntity<?> updateConsumption(@RequestBody ConsumptionPutRequest request) {

        // Buscar consumo
        Consumption consumption = consumptionDAO.findById(request.getConsumption_id()).orElse(null);

        if (consumption == null) {
            return ResponseEntity.status(404).body("Consumo não encontrado");
        }

        // Atualizar conta (se enviada)
        if (request.getConta_id() != null) {
            Account account = accountDAO.findById(request.getConta_id()).orElse(null);

            if (account == null) {
                return ResponseEntity.status(404).body("Conta não existe!");
            }

            consumption.setAccount(account);
        }

        // Atualizar item (se enviado)
        if (request.getNum_item() != null) {
            Item item = itemDAO.findById(request.getNum_item()).orElse(null);

            if (item == null) {
                return ResponseEntity.status(404).body("Item não existe!");
            }

            consumption.setItem(item);
        }

        // Atualizar quantidade (se enviada)
        if (request.getQuantidade() != null) {
            consumption.setQuantity(request.getQuantidade());
        }

        // Salvar mudanças diretamente
        consumptionDAO.save(consumption);

        return ResponseEntity.ok(consumption);
    }

    @DeleteMapping("/consumptions") // Delete all consumptions
    public void deleteAllConsumptions(){
        consumptionDAO.deleteAll();
    }

    @DeleteMapping("/consumptions/{id}") // Delete consumption by id
    public void deleteConsumption(@PathVariable int id)
    {
        consumptionDAO.deleteById(id);
    }
}
