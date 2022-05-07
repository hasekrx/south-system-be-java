package com.southsystem.dataanalysis.core.service;


import com.southsystem.dataanalysis.DataAnalysisApplication;
import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.exception.InvalidLineCodeException;
import com.southsystem.dataanalysis.service.EntryFileService;
import com.southsystem.dataanalysis.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(classes = {DataAnalysisApplication.class, EntryFileService.class})
public class EntryFileServiceTest {

    @Autowired
    private EntryFileService entryFileService;

    @Test
    public void testFileRead() throws IOException {
        Integer expectedSalesSize = 2;
        Integer expectedClientSize = 2;
        Integer expectedSalesManSize = 3;

        String content = TestUtils.getTestFile("sample-test.dat");

        FileData fileData = entryFileService.process(content);

        Assertions.assertNotNull(fileData);
        Assertions.assertEquals(expectedSalesSize, fileData.getSales().size());
        Assertions.assertEquals(expectedSalesManSize, fileData.getSalesMan().size());
        Assertions.assertEquals(expectedClientSize, fileData.getClients().size());
    }

    @Test
    public void shouldThrowInvalidCodeException() throws IOException {
        String content = TestUtils.getTestFile("sample-invalid-code.dat");

        var thrown =
                Assertions.assertThrows(InvalidLineCodeException.class, () -> entryFileService.process(content));

        Assertions.assertEquals("invalid code 005", thrown.getMessage());
    }

}
