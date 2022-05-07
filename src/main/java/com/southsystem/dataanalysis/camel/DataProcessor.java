package com.southsystem.dataanalysis.camel;

import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.service.DoneFileService;
import com.southsystem.dataanalysis.service.EntryFileService;
import com.southsystem.dataanalysis.service.ReadFileService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DataProcessor implements Processor {

    private final ReadFileService readFileService;
    private final EntryFileService entryFileService;
    private final DoneFileService doneFileService;

    public DataProcessor(ReadFileService readFileService, EntryFileService entryFileService, DoneFileService doneFileService) {
        this.readFileService = readFileService;
        this.entryFileService = entryFileService;
        this.doneFileService = doneFileService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        String absolutePath = exchange.getMessage()
                .getHeader("CamelFileAbsolutePath")
                .toString();

        Path path = Paths.get(absolutePath);

        String content = readFileService.readFile(path);

        FileData fileData = entryFileService.process(content);
        doneFileService.createReport(fileData, path);
    }
}
