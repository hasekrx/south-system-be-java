package com.southsystem.dataanalysis.core.impl;

import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.core.model.SalesMan;
import com.southsystem.dataanalysis.core.processor.impl.SalesManProcessor;
import com.southsystem.dataanalysis.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class SalesManProcessorTest {

    @Test
    public void shouldReturnLineData() {

        SalesMan expectedSaleMan =
                TestUtils.buildSalesMan("1234567891234", "Pedro", new BigDecimal("50000"));

        FileData emptyFileData = TestUtils.createEmptyFileData();
        SalesManProcessor salesManProcessor = new SalesManProcessor();
        String line = "001ç1234567891234çPedroç50000";
        salesManProcessor.processLine(line, emptyFileData);

        Assertions.assertEquals(expectedSaleMan, emptyFileData.getSalesMan().get(0));
    }
}
