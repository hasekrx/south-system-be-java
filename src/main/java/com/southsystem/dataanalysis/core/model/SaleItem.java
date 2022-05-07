package com.southsystem.dataanalysis.core.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SaleItem {
    private String itemId;
    private int quantity;
    private BigDecimal price;

    public BigDecimal total() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
