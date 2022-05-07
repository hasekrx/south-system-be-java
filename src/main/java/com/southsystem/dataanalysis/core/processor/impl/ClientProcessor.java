package com.southsystem.dataanalysis.core.processor.impl;

import com.southsystem.dataanalysis.core.model.Client;
import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.core.processor.LineCodeType;
import com.southsystem.dataanalysis.core.processor.LineProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ClientProcessor implements LineProcessor {

    private static final int CNPJ = 1;
    private static final int NAME = 2;
    private static final int BUSSINES_AREA = 3;

    @Override
    public LineCodeType getLineCode() {
        return LineCodeType.CLIENT;
    }

    @Override
    public void processLine(String line, FileData fileData) {
        var clientData = line.split(DELIMITER);

        Client client =  Client.builder()
                .cnpj(clientData[CNPJ])
                .name(clientData[NAME])
                .bussinesArea(clientData[BUSSINES_AREA])
                .build();

        fileData.getClients().add(client);
    }
}
