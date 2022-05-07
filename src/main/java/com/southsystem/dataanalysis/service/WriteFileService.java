package com.southsystem.dataanalysis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class WriteFileService {

        public void writeFile(Path path, String content) throws IOException {
                Files.writeString(path, content, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        }
}
