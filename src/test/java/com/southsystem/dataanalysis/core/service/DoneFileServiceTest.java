package com.southsystem.dataanalysis.core.service;

import com.southsystem.dataanalysis.config.AppConfiguration;
import com.southsystem.dataanalysis.core.model.Client;
import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.core.model.SaleItem;
import com.southsystem.dataanalysis.core.model.Sales;
import com.southsystem.dataanalysis.core.model.SalesMan;
import com.southsystem.dataanalysis.service.DoneFileService;
import com.southsystem.dataanalysis.service.WriteFileService;
import com.southsystem.dataanalysis.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoneFileServiceTest {

    @Mock
    WriteFileService fileWriterService;
    @Mock
    AppConfiguration appConfiguration;

    @InjectMocks
    DoneFileService doneFileService;

    @Test
    public void verifyDoneFileService_correctPath() throws IOException {
        FileData fileData = mockFileData();
        when(appConfiguration.getHomePath()).thenReturn("home/");

        doneFileService.createReport(fileData, Paths.get("home/data/in/test-file.dat"));

        verify(fileWriterService).writeFile(eq(Path.of("home/data/out/test-file.done.dat")), any());
    }

    private FileData mockFileData() {
        SaleItem item = TestUtils.buildSaleItem("1",10, new BigDecimal(100));
        Sales sales = TestUtils.buildSales("1", List.of(item), "TestUser");
        SalesMan salesMan =  TestUtils.buildSalesMan("303003", "TestUser", new BigDecimal(100));
        Client client = TestUtils.buildClient("3333", "TestClient", "bussinesArea");

        return FileData.builder()
                .clients(List.of(client))
                .salesMan(List.of(salesMan))
                .sales(List.of(sales)).build();
    }


}
