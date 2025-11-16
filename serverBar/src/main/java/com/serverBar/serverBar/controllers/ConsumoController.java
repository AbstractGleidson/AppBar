package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ConsumoInterface;
import com.serverBar.serverBar.DAOs.ContaInterface;
import com.serverBar.serverBar.DAOs.ItemInterface;
import com.serverBar.serverBar.Request.ConsumptionRequest;
import com.serverBar.serverBar.models.Consumo;
import com.serverBar.serverBar.models.Conta;
import com.serverBar.serverBar.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ConsumoController {

    @Autowired
    private ConsumoInterface consumptionDAO; // Automatically generates DAOs methods
    @Autowired
    private ContaInterface accountDAO;
    @Autowired
    private ItemInterface itemDAO;

    @GetMapping("/consumptions")
    public ArrayList<Consumo> getConsumptions() // Recover all database consumptions
    {
        // Return List of consumptions or Empty List
        return (ArrayList<Consumo>) consumptionDAO.findAll();
    }

    @GetMapping("/consumption/{id}") // Search consumption by id
    public Optional<Consumo> searchConsumption(@PathVariable int id)
    {
        // Return consumption or Null
        return consumptionDAO.findById(id);
    }

    @GetMapping("/consumption/account/{id}") // Search account consumptions
    public ArrayList<Consumo> searchConsumptionByAccount(@PathVariable int id)
    {
        // Return List of consumptions or Empty List
        return consumptionDAO.findByContaId(id);
    }

    @GetMapping("consumption/account/client/{name}")
    public ArrayList<Consumo> searchConsumptionsByAccountClientName(@PathVariable String name)
    {
        return consumptionDAO.findByContaClienteName(name);
    }

    @PostMapping("/consumption") // Save one new consumption
    public ResponseEntity<Consumo> postConsumption(@RequestBody ConsumptionRequest consumptionRequest)
    {
        Optional<Conta> account = accountDAO.findById(consumptionRequest.getConta_id());
        Optional<Item>  item = itemDAO.findById(consumptionRequest.getNum_item());

        if(account.isEmpty())
            ResponseEntity.status(404).body("Conta não existe!");
        if(item.isEmpty())
            ResponseEntity.status(404).body("Item não existe!");

        Conta newAccount = account.get();
        Item newItem = item.get();

        Consumo consumption = new Consumo();
        consumption.setId(0);
        consumption.setConta(newAccount);
        consumption.setItem(newItem);
        consumption.setQuantidade(consumptionRequest.getQuantidade());

        // return new consumption
        return ResponseEntity.status(200).body(consumptionDAO.save(consumption));
    }

    @PutMapping("/consumption") // Updates a customer information
    public ResponseEntity<Consumo> updateConsumption(@RequestBody ConsumptionRequest consumptionRequest)
    {
        Optional<Conta> account = accountDAO.findById(consumptionRequest.getConta_id());
        Optional<Item>  item = itemDAO.findById(consumptionRequest.getNum_item());

        if(account.isEmpty())
            ResponseEntity.status(404).body("Conta não existe!");
        if(item.isEmpty())
            ResponseEntity.status(404).body("Item não existe!");

        Conta newAccount = account.get();
        Item newItem = item.get();

        Consumo consumption = new Consumo();
        consumption.setId(0);
        consumption.setConta(newAccount);
        consumption.setItem(newItem);
        consumption.setQuantidade(consumptionRequest.getQuantidade());

        // return new consumption
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
