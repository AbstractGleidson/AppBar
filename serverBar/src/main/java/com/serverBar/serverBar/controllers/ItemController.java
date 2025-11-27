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
    public Item postItem(@RequestBody ItemPostResquest itemResquest)// Save one new item
    {
        Item item = new Item();

        // Evita conflicts com o json
        item.setAvailable(itemResquest.isAvailable());
        item.setName(itemResquest.getName());
        item.setNumber_item(itemResquest.getNumber_item());
        item.setType(itemResquest.getType());
        item.setValue(itemResquest.getValue());

        return DAO.save(item); // Return new item
    }

    @PutMapping("/item")
    public ResponseEntity<?> updateItem(@RequestBody ItemPutRequest request) // Updates a customer information
    {
        Item item = DAO.findById(request.getNumber_item()).orElse(null);

        if(item == null)
            return ResponseEntity.status(404).body("Não foi possível atualizar item, pois ele não existe!");

        if(request.getAvailable() != null)
            item.setAvailable(request.getAvailable());

        if(request.getName() != null)
            item.setName(request.getName());

        if(request.getType() != null)
            item.setType(request.getType());

        if(request.getValue() != null)
            item.setValue(request.getValue());

        return ResponseEntity.ok().body(DAO.save(item)); // Return update item
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

    @GetMapping("/Item/more/sale")
    public ResponseEntity<?> getMoreSale()
    {
        ArrayList<Object[]> top = DAO.findMoreSale(PageRequest.of(0, 10));

        ArrayList<ItemMoreSaleRequest> report = new ArrayList<>();

        for(Object[] t: top)
        {
            ItemMoreSaleRequest item = new ItemMoreSaleRequest();

            item.setItem_id((Number) t[0]);
            item.setName((String) t[1]);
            item.setQuantity((Number) t[2]);

            report.add(item);
        }

        return ResponseEntity.ok().body(report);
    }

    @GetMapping("/item/more/revenue")
    public ResponseEntity<?> getItemRevenueReport() {
        // Chama o top 10 de faturamento
        ArrayList<Object[]> result = DAO.findMoreRevenue(
                PageRequest.of(0, 10) // TOP 10
        );

        // Monta a resposta
        ArrayList<ItemMoreRevenueRequest> report = new ArrayList<>();

        for (Object[] row : result) {
            ItemMoreRevenueRequest request = new ItemMoreRevenueRequest();

            request.setItem_id(((Number) row[0]).intValue());
            request.setName((String) row[1]);
            request.setRevenue(((Number) row[2]).doubleValue());

            report.add(request);
        }

        return ResponseEntity.ok(report);
    }

}