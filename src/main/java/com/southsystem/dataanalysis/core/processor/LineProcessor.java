package com.southsystem.dataanalysis.core.processor;

import com.southsystem.dataanalysis.core.model.FileData;

public interface LineProcessor {
    String DELIMITER = "ç";

    LineCodeType getLineCode();
    void processLine(String line, FileData fileData);
}
