package com.southsystem.dataanalysis.core.model;

import com.southsystem.dataanalysis.exception.SaleException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileData {

    List<Sales> sales;
    List<SalesMan> salesMan;
    List<Client> clients;

    public String getHigherSaleId() {
        return this.getSales().stream()
                .max(Comparator.comparing(Sales::salesTotal))
                .map(Sales::getSaleId)
                .orElseThrow(() -> new SaleException("There are no sales on the file"));
    }

    public String getWorstSalesMan() {
        List<Sales> sales = this.getSales();
        if(sales.isEmpty()) {
            throw new SaleException("There are no sales on the file");
        }

        var mapSales = new HashMap<String, BigDecimal>();
        sales.forEach(sale -> {
            String salesManName = sale.getSalesManName();
            if(mapSales.get(sale.getSalesManName()) != null) {
                mapSales.put(salesManName, mapSales.get(salesManName).add(sale.salesTotal()));
            } else {
                mapSales.put(salesManName, sale.salesTotal());
            }
        });

        return Collections.min(mapSales.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
