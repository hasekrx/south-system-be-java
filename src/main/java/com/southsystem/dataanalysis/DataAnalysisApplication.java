package com.southsystem.dataanalysis;

import com.southsystem.dataanalysis.config.AppConfiguration;
import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.service.DoneFileService;
import com.southsystem.dataanalysis.service.EntryFileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
public class DataAnalysisApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataAnalysisApplication.class, args);
	}
}
