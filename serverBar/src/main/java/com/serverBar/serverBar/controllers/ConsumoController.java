package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ConsumoInterface;
import com.serverBar.serverBar.models.Consumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ConsumoController {

    @Autowired
    private ConsumoInterface DAO; // Automatically generates DAOs methods

    @GetMapping("/consumptions")
    public ArrayList<Consumo> getConsumptions() // Recover all database consumptions
    {
        // Return List of consumptions or Empty List
        return (ArrayList<Consumo>) DAO.findAll();
    }

    @GetMapping("/consumption/{id}") // Search consumption by id
    public Optional<Consumo> searchConsumption(@PathVariable int id)
    {
        // Return consumption or Null
        return DAO.findById(id);
    }

    @GetMapping("/consumption/account/{id}") // Search account consumptions
    public ArrayList<Consumo> searchConsumptionByAccount(@PathVariable int id)
    {
        // Return List of consumptions or Empty List
        return DAO.findByContaId(id);
    }

    @PostMapping("/consumption") // Save one new consumption
    public Consumo postConsumption(@RequestBody Consumo consumo)
    {
        // return new consumption
        return DAO.save(consumo);
    }

    @PutMapping("/consumption") // Updates a customer information
    public Consumo updateConsumption(@RequestBody Consumo consumo)
    {
        return DAO.save(consumo); // return update consumption
    }

    @DeleteMapping("/consumptions") // Delete all consumptions
    public void deleteAllConsumptions(){
        DAO.deleteAll();
    }

    @DeleteMapping("/consumptions/{id}") // Delete consumption by id
    public void deleteConsumption(@PathVariable int id)
    {
        DAO.deleteById(id);
    }
}
