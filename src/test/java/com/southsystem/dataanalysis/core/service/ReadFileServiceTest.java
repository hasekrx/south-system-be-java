package com.southsystem.dataanalysis.core.service;

import com.southsystem.dataanalysis.exception.EmptyFileException;
import com.southsystem.dataanalysis.service.ReadFileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class ReadFileServiceTest {

    @Test
    public void shouldReadTheFile() throws IOException {
        String expectedContent = "testing-read";
        ReadFileService readFileService = new ReadFileService();
        String content =
                readFileService.readFile(Paths.get("src", "test", "resources", "sample-read-file"));

        Assertions.assertEquals(expectedContent, content);
    }

    @Test
    public void shouldThrowException_whenEmptyFile() {
        ReadFileService readFileService = new ReadFileService();
        var thrown = Assertions.assertThrows(EmptyFileException.class, () -> readFileService.readFile(Paths.get("src", "test", "resources", "empty-file")));

        Assertions.assertEquals("empty file", thrown.getMessage());
    }
}
