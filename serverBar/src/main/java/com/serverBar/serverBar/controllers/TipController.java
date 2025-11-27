package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.Request.TipRequest;
import com.serverBar.serverBar.Services.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/tips")
public class TipController {

    @Autowired
    private TipService tipService;

    @GetMapping
    public ResponseEntity<TipRequest> getTips() throws IOException {
        return ResponseEntity.ok(tipService.loadTipsPercents());
    }

    @PostMapping
    public ResponseEntity<String> upDateTips(@RequestBody TipRequest request) throws  IOException {
        tipService.saveTips(request);

        return  ResponseEntity.ok(String.format("Porcetagem de gorjeta atualizada: Comida - %.2f%%, Bebida - %.2f%%", request.getTipPercentFood(), request.getTipPercentDrink()));
    }
}
