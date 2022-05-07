package com.southsystem.dataanalysis.core.processor.impl;

import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.core.model.SalesMan;
import com.southsystem.dataanalysis.core.processor.LineCodeType;
import com.southsystem.dataanalysis.core.processor.LineProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Component
public class SalesManProcessor implements LineProcessor {

    private static final int CPF = 1;
    private static final int NAME = 2;
    private static final int SALARY = 3;

    @Override
    public LineCodeType getLineCode() {
        return LineCodeType.SALESMAN;
    }

    @Override
    public void processLine(String line, FileData fileData) {
        final var salesManData = line.split(DELIMITER);

        SalesMan salesMan = SalesMan.builder()
                .cpf(salesManData[CPF])
                .name(salesManData[NAME])
                .salary(new BigDecimal(salesManData[SALARY]))
                .build();

        fileData.getSalesMan().add(salesMan);
    }
}
