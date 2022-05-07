package com.southsystem.dataanalysis.core.processor.impl;

import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.core.model.SaleItem;
import com.southsystem.dataanalysis.core.model.Sales;
import com.southsystem.dataanalysis.core.processor.LineCodeType;
import com.southsystem.dataanalysis.core.processor.LineProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalesProcessor implements LineProcessor {

    private static int SALES_ID = 1;
    private static int SALES_ITEM = 2;
    private static int SALESMAN_NAME = 3;

    private static String ITEM_DELIMITER = ",";
    private static String ITEM_FIELD_DELIMITIR = "-";

    private static int ITEM_ID = 0;
    private static int ITEM_QUANTITY = 1;
    private static int ITEM_PRICE = 2;

    @Override
    public LineCodeType getLineCode() {
        return LineCodeType.SALES;
    }

    @Override
    public void processLine(String line, FileData fileData) {
        var salesData = line.split(DELIMITER);

        Sales sales = Sales.builder()
                .saleId(salesData[SALES_ID])
                .saleItems(createSales(salesData[SALES_ITEM]))
                .salesManName(salesData[SALESMAN_NAME])
                .build();

        fileData.getSales().add(sales);
    }

    private List<SaleItem> createSales(String salesItems) {
        var salesArray = salesItems.substring(1, salesItems.length() - 1).split(ITEM_DELIMITER);

        ArrayList<SaleItem> sales = new ArrayList<>();


        for (String item : salesArray) {
            var sale = item.substring(0, item.length()).split(ITEM_FIELD_DELIMITIR);

            var saleItem = SaleItem.builder()
                    .itemId(sale[ITEM_ID])
                    .quantity(Integer.parseInt(sale[ITEM_QUANTITY]))
                    .price(new BigDecimal(sale[ITEM_PRICE]))
                    .build();

            sales.add(saleItem);
        }

        return sales;
    }
}
