package com.southsystem.dataanalysis.service;

import com.southsystem.dataanalysis.config.AppConfiguration;
import com.southsystem.dataanalysis.exception.EmptyFileException;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ReadFileService {

    public String readFile(Path absolutePath) throws IOException {
        var bytes = Files.readAllBytes(absolutePath);
        String content = new String(bytes, StandardCharsets.UTF_8);

        if (StringUtils.isBlank(content)) {
            throw new EmptyFileException("empty file");
        }

        return content;
    }
}
