package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ConsumptionInterface;
import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.DAOs.ItemInterface;
import com.serverBar.serverBar.Request.ConsumptionRequest;
import com.serverBar.serverBar.models.Consumption;
import com.serverBar.serverBar.models.Account;
import com.serverBar.serverBar.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> postConsumption(@RequestBody ConsumptionRequest consumptionRequest)
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

    // ======================================================================
    // You need to find this method, it is not updating the values just
    // by creating a new account
    // ======================================================================
    @PutMapping("/consumption") // Updates a customer information
    public ResponseEntity<?> updateConsumption(@RequestBody ConsumptionRequest consumptionRequest)
    {
        // Check if account exits
        Optional<Account> account = accountDAO.findById(consumptionRequest.getConta_id());
        // Check if item exits
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
