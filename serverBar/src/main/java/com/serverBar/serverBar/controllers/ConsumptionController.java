package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ConsumptionInterface;
import com.serverBar.serverBar.DAOs.AccountInterface;
import com.serverBar.serverBar.DAOs.ItemInterface;
import com.serverBar.serverBar.Request.ConsumptionRequest.ConsumptionIntervalReport;
import com.serverBar.serverBar.Request.ConsumptionRequest.ConsumptionIntervalRequest;
import com.serverBar.serverBar.Request.ConsumptionRequest.ConsumptionPostRequest;
import com.serverBar.serverBar.Request.ConsumptionRequest.ConsumptionPutRequest;
import com.serverBar.serverBar.Request.PayRequest.PayRevenueRequest;
import com.serverBar.serverBar.Services.PayService.PayIntervalCalculationService;
import com.serverBar.serverBar.models.Consumption;
import com.serverBar.serverBar.models.Account;
import com.serverBar.serverBar.models.Item;
import com.serverBar.serverBar.models.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private PayIntervalCalculationService payIntervalCalculationService;

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
        try {
            // Check if the account exists
            Account account = accountDAO.findById(consumptionRequest.getConta_id()).orElse(null);
            // Check if the item exists
            Item item = itemDAO.findById(consumptionRequest.getNum_item()).orElse(null);

            // Account not exists
            if (account == null)
                return ResponseEntity.status(404).body("Conta não existe!");
            // Item not exists
            if (item == null)
                return ResponseEntity.status(404).body("Item não existe!");

            if(!item.isAvailable())
                return ResponseEntity.status(404).body("O pedido não está disponivel!");

            // Constructs consumption
            Consumption consumption = new Consumption();
            consumption.setId(0); // id is auto generated
            consumption.setAccount(account);
            consumption.setItem(item);
            consumption.setQuantity(consumptionRequest.getQuantidade());

            // return new consumption and server response
            return ResponseEntity.status(200).body(consumptionDAO.save(consumption));
        }catch (Exception e)
        {
            return ResponseEntity.status(500).body("Erro: " + e);
        }
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

    @GetMapping("/consumption/interval")
    public ResponseEntity<?> getBarConsumptionInterval(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        try {

            // Formatos possiveis
            DateTimeFormatter formatBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatUS = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            LocalDate start;
            LocalDate end;

            // Tenta achar um dos tipos de formato de data Us ou BR
            try {
                start = LocalDate.parse(startDate, formatBR);
                end = LocalDate.parse(endDate, formatBR);
            } catch (Exception e1) {
                // Tenta um se nao der tenta outro
                try {
                    start = LocalDate.parse(startDate, formatUS);
                    end = LocalDate.parse(endDate, formatUS);
                } catch (Exception e2) {
                    return ResponseEntity.badRequest().body("Erro: formato de data inválido. Use dd/MM/yyyy ou MM/dd/yyyy.");
                }
            }

            // Se a data inicial for maior que a final, inverte
            if (start.isAfter(end)) {
                LocalDate temp = start;
                start = end;
                end = temp;
            }

            LocalDateTime startDateTime = start.atStartOfDay();
            LocalDateTime endDateTime = end.atTime(23, 59, 59);

            DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            ArrayList<Consumption> consumptions = consumptionDAO.findAllByDateBetween(startDateTime, endDateTime);
            ArrayList<ConsumptionIntervalRequest> request = new ArrayList<>();

            PayRevenueRequest payRequest = payIntervalCalculationService.intervalPayCalculation(
                    startDateTime.format(formatterOut),
                    endDateTime.format(formatterOut)
            );

            // Montar resposta
            for (Consumption c : consumptions) {
                ConsumptionIntervalRequest r = new ConsumptionIntervalRequest();

                r.setDate(c.getDate().format(formatBR));
                r.setQuantity(c.getQuantity());
                r.setNameItem(c.getItem().getName());
                r.setClient_cpf(c.getAccount().getClient().getCpf());

                request.add(r);
            }

            ConsumptionIntervalReport report = new ConsumptionIntervalReport();
            report.setConsumptions(request);
            report.setRevenue(payRequest.getRevenue());

            return ResponseEntity.ok(report);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }

}
