package com.southsystem.dataanalysis.service;

import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.core.processor.impl.LineProcessFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class EntryFileService {

    private static final int LINE_FIRST_INDEX = 0;
    private static final int LINE_THIRD_INDEX = 3;
    private static final String DELIMITER = "\n";

    private LineProcessFactory lineProcessFactory;

    public EntryFileService(LineProcessFactory lineProcessFactory) {
        this.lineProcessFactory = lineProcessFactory;
    }

    public FileData process(String content) {
        var lines = Arrays.asList(content.split(DELIMITER));

        FileData fileData = FileData.builder()
                .sales(new ArrayList<>())
                .salesMan(new ArrayList<>())
                .clients(new ArrayList<>()).build();

        lines.stream()
            .map(String::trim)
            // getting line processor implementation based on the line code. example 001
            .forEach(line -> lineProcessFactory.getLineProcessor(line.substring(LINE_FIRST_INDEX, LINE_THIRD_INDEX)).processLine(line, fileData));

        return fileData;
    }
}
