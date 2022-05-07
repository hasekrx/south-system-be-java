package com.southsystem.dataanalysis.core.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class Sales {

    private String saleId;
    private List<SaleItem> saleItems;
    private String salesManName;

    public BigDecimal salesTotal() {
        BigDecimal total = new BigDecimal(0);
        this.saleItems.forEach(item -> total.add(item.total()));

        return total;
    }
}
