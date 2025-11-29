package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ItemInterface;
import com.serverBar.serverBar.Request.BarResumeRequest;
import com.serverBar.serverBar.Request.ItemRequest.ItemMoreRevenueRequest;
import com.serverBar.serverBar.Request.ItemRequest.ItemMoreSaleRequest;
import com.serverBar.serverBar.Request.TipRequest.TipRequest;
import com.serverBar.serverBar.Services.BarResumeService;
import com.serverBar.serverBar.Services.ItemResumeService;
import com.serverBar.serverBar.Services.TipService;
import com.serverBar.serverBar.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class BarController {

    @Autowired
    private BarResumeService barResumeService;
    @Autowired
    private TipService tipService;
    @Autowired
    private ItemInterface itemDAO;
    @Autowired
    private ItemResumeService itemResumeService;

    // Resumo sobre o bar
    @GetMapping("bar/resume")
    public ResponseEntity<BarResumeRequest> getBarResume(@RequestParam String startDate, @RequestParam String endDate)
    {
        return ResponseEntity.ok().body(barResumeService.getBarResume(startDate, endDate));
    }

    @GetMapping("bar/tip")
    public ResponseEntity<TipRequest> getTips() throws IOException {
        return ResponseEntity.ok(tipService.loadTipsPercents());
    }

    @PostMapping("bar/tip")
    public ResponseEntity<String> upDateTips(@RequestBody TipRequest request) throws  IOException {
        tipService.saveTips(request);

        return  ResponseEntity.ok(String.format("Porcetagem de gorjeta atualizada: Comida - %.2f%%, Bebida - %.2f%%", request.getTipPercentFood(), request.getTipPercentDrink()));
    }

    @GetMapping("/bar/item/more/sale")
    public ResponseEntity<?> getMoreSale()
    {
        ArrayList<ItemMoreSaleRequest> report = itemResumeService.getMoreSale();

        return ResponseEntity.ok().body(report);
    }

    @GetMapping("/bar/item/more/revenue")
    public ResponseEntity<?> getItemRevenueReport() {
        // Monta a resposta
        ArrayList<ItemMoreRevenueRequest> report = itemResumeService.getItemRevenueReport();

        return ResponseEntity.ok(report);
    }

    // Get para o covert
    @GetMapping("/bar/covert")
    public Item getCovert()
    {
        return itemDAO.findById(0).orElse(null);
    }

    // Cria o covert como item
    @PostMapping("/bar/covert/{valueCovert}")
    public ResponseEntity<Item> createdCovert(@PathVariable double valueCovert)
    {
        Item covert = itemDAO.findById(0).orElse(null);

        if(covert == null) {
            covert = new Item();

            covert.setValue(valueCovert);
            covert.setType(0);
            covert.setName("Ingresso");
            covert.setNumber_item(0);
            covert.setAvailable(true);

            return ResponseEntity.ok().body(itemDAO.save(covert));
        }

        return ResponseEntity.ok().body(covert);
    }

    // Cria o covert como item
    @PutMapping("/bar/covert/{valueCovert}")
    public ResponseEntity<Item> updateCovert(@PathVariable double valueCovert)
    {
        Item covert = itemDAO.findById(0).orElse(null);

        if(covert == null) {
            covert = new Item();
            covert.setValue(valueCovert);
            covert.setType(0);
            covert.setName("Ingresso");
            covert.setNumber_item(0);
            covert.setAvailable(true);
        }
        else
            covert.setValue(valueCovert);

        return ResponseEntity.ok().body(itemDAO.save(covert));
    }


}
