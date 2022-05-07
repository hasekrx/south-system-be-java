package com.southsystem.dataanalysis.core.impl;

import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.core.model.SaleItem;
import com.southsystem.dataanalysis.core.model.Sales;
import com.southsystem.dataanalysis.core.processor.impl.SalesProcessor;
import com.southsystem.dataanalysis.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SalesProcessorTest {

    @Test
    public void shouldReturnLineDataWiThOneSale() {
        SaleItem item1 =
                TestUtils.buildSaleItem("1", 10,new BigDecimal("100"));

        Sales expectedSale =
                TestUtils.buildSales("10", List.of(item1), "Pedro");

        FileData emptyFileData = TestUtils.createEmptyFileData();
        
        String line = "003ç10ç[1-10-100]çPedro";
        SalesProcessor salesProcessor = new SalesProcessor();
        salesProcessor.processLine(line, emptyFileData);

        Assertions.assertEquals(expectedSale, emptyFileData.getSales().get(0));
    }

    @Test
    public void shouldReturnLineDataWiThreeSales() {
        SaleItem item1 =
                TestUtils.buildSaleItem("1", 34,new BigDecimal("10"));

        SaleItem item2 =
                TestUtils.buildSaleItem("2", 33,new BigDecimal("1.50"));

        SaleItem item3 =
                TestUtils.buildSaleItem("3", 40,new BigDecimal("0.10"));

        Sales expectedSale =
                TestUtils.buildSales("08", Arrays.asList(item1, item2, item3), "Paulo");

        FileData emptyFileData = TestUtils.createEmptyFileData();

        String line = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
        SalesProcessor salesProcessor = new SalesProcessor();
        salesProcessor.processLine(line, emptyFileData);

        Assertions.assertEquals(expectedSale, emptyFileData.getSales().get(0));
    }
}
