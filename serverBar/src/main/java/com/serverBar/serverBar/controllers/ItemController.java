package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ItemInterface;
import com.serverBar.serverBar.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ItemController {

    @Autowired
    private ItemInterface DAO; // Automatically generates DAOs methods

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
    public Item postItem(@RequestBody Item item) // Save one new item
    {
        return DAO.save(item); // Return new item
    }

    @PutMapping("/item")
    public Item updateItem(@RequestBody Item item) // Updates a customer information
    {
        return DAO.save(item); // Return update item
    }

    @DeleteMapping("/cardapio")
    public void deleteItems() // Delete Cardapio
    {
        DAO.deleteAll();
    }

    @DeleteMapping("/Item/{num_item}")
    public void deleteItem(@PathVariable int num_item) // Delete item for num_item
    {
        DAO.deleteById(num_item);
    }
}