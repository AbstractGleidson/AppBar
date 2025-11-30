package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ConsumptionInterface;
import com.serverBar.serverBar.DAOs.ItemInterface;
import com.serverBar.serverBar.Request.ItemRequest.ItemMoreRevenueRequest;
import com.serverBar.serverBar.Request.ItemRequest.ItemMoreSaleRequest;
import com.serverBar.serverBar.Request.ItemRequest.ItemPostResquest;
import com.serverBar.serverBar.Request.ItemRequest.ItemPutRequest;
import com.serverBar.serverBar.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ItemController {

    @Autowired
    private ItemInterface DAO; // Automatically generates DAOs methods
    @Autowired
    private ConsumptionInterface consumptionDAO;

    @GetMapping("/cardapio")
    public ArrayList<Item> getItems() // Recover Cardapio on database
    {
        // List of items or empty list
        return (ArrayList<Item>) DAO.findAll();
    }

    @GetMapping("/item/{num_item}")
    public Optional<Item> searchItem(@PathVariable int num_item) // Search Item by num_Item
    {
        // Item or Null
        return DAO.findById(num_item); // Return the item if it exists in the database
    }

    @PostMapping("/item")
    public ResponseEntity<?> postItem(@RequestBody ItemPostResquest itemResquest)// Save one new item
    {
        if(itemResquest.getNumber_item() == 0)
            return ResponseEntity.status(500).body("O ingresso não pode ser criado por este endpoint!");

        Item item = new Item();

        // Evita conflicts com o json
        item.setAvailable(itemResquest.isAvailable());
        item.setName(itemResquest.getName());
        item.setNumber_item(itemResquest.getNumber_item());
        item.setType(itemResquest.getType());
        item.setValue(itemResquest.getValue());

        return ResponseEntity.ok().body(DAO.save(item)); // Return new item
    }

    @PutMapping("/item")
    public ResponseEntity<?> updateItem(@RequestBody ItemPutRequest request) // Updates a customer information
    {
        try {
            if (request.getNumber_item() == 0)
                return ResponseEntity.status(500).body("O ingresso não pode ser criado por este endpoint!");

            Item item = DAO.findById(request.getNumber_item()).orElse(null);

            if (!(item == null)) {
                if (request.getAvailable() != null)
                    item.setAvailable(request.getAvailable());

                if (request.getName() != null)
                    item.setName(request.getName());

                if (request.getType() != null)
                    item.setType(request.getType());

                if (request.getValue() != null)
                    item.setValue(request.getValue());
            } else {
                if (request.getValue() != null && request.getName() != null && request.getType() != null) {
                    item = new Item();

                    item.setNumber_item(request.getNumber_item());
                    item.setValue(request.getValue());
                    item.setType(request.getType());
                    item.setName(request.getName());
                    item.setAvailable(true);
                }
            }

            if (item == null)
                return ResponseEntity.status(404).body("Tentando criar um item com dados incompletos!");


            return ResponseEntity.ok(DAO.save(item)); // Return update item
        }catch (Exception e)
        {
            return ResponseEntity.status(500).body("Erro: " + e);
        }
    }

    @DeleteMapping("/cardapio")
    public void deleteItems() // Delete Cardapio
    {
        DAO.deleteAll();
    }

    @DeleteMapping("/Item/{num_item}")
    public void deleteItem(@PathVariable int num_item) // Delete item by num_item
    {
        DAO.deleteById(num_item);
    }
}