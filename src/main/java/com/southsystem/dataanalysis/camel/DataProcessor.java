package com.southsystem.dataanalysis.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class DataProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        String absolutePath = exchange.getMessage()
                .getHeader("CamelFileAbsolutePath")
                .toString();
        System.out.println(absolutePath);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
        var bytes = Files.readAllBytes(Path.of(absolutePath));
        String content = new String(bytes, StandardCharsets.UTF_8);

        System.out.println(content);
    }
}
