package com.southsystem.dataanalysis.core.impl;

import com.southsystem.dataanalysis.core.model.Client;
import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.core.processor.impl.ClientProcessor;
import com.southsystem.dataanalysis.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientProcessorTest {

    @Test
    public void shouldReturnLineData() {

        Client expectedClient =
                TestUtils.buildClient("2345675434544345", "Jose da Silva", "Rural");

        FileData emptyFileData = TestUtils.createEmptyFileData();

        String line = "002ç2345675434544345çJose da SilvaçRural";
        ClientProcessor clientProcessor = new ClientProcessor();
        clientProcessor.processLine(line, emptyFileData);

        Assertions.assertEquals(expectedClient, emptyFileData.getClients().get(0));
    }
}
