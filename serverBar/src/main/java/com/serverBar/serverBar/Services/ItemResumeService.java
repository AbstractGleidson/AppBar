package com.serverBar.serverBar.Services;

import com.serverBar.serverBar.DAOs.ItemInterface;
import com.serverBar.serverBar.Request.ItemRequest.ItemMoreRevenueRequest;
import com.serverBar.serverBar.Request.ItemRequest.ItemMoreSaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemResumeService {
    @Autowired
    private ItemInterface itemDAO;

    public ArrayList<ItemMoreSaleRequest> getMoreSale()
    {
        ArrayList<Object[]> top = itemDAO.findMoreSale(PageRequest.of(0, 10));

        ArrayList<ItemMoreSaleRequest> report = new ArrayList<>();

        for(Object[] t: top)
        {
            // Tira o ingresso da listagem
            if(((int) t[0]) != 0) {
                ItemMoreSaleRequest item = new ItemMoreSaleRequest();

                item.setItem_id((int) t[0]);
                item.setName((String) t[1]);
                item.setQuantity((int) t[2]);

                report.add(item);
            }
        }

        return report;
    }

    public ArrayList<ItemMoreRevenueRequest> getItemRevenueReport() {
        // Chama o top 10 de faturamento
        ArrayList<Object[]> top = itemDAO.findMoreRevenue(
                PageRequest.of(0, 10) // TOP 10
        );

        // Monta a resposta
        ArrayList<ItemMoreRevenueRequest> report = new ArrayList<>();

        for (Object[] t : top) {

            if(((int) t[0]) != 0){
                ItemMoreRevenueRequest request = new ItemMoreRevenueRequest();

                request.setItem_id(((int) t[0]));
                request.setName((String) t[1]);
                request.setRevenue(((double) t[2]));

                report.add(request);
            }
        }

        return report;
    }
}
