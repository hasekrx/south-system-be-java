package com.southsystem.dataanalysis;

import com.southsystem.dataanalysis.camel.DataProcessor;
import com.southsystem.dataanalysis.config.AppConfiguration;
import lombok.extern.slf4j.Slf4j;
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
public class DataAnalysisApplication implements CommandLineRunner {

	private final AppConfiguration appConfiguration;

	public DataAnalysisApplication(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
	}

	public static void main(String[] args) {
		SpringApplication.run(DataAnalysisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Stream<Path> pathStream = Files.list(Paths.get("C:/Users/alexa/Desktop/system-south-read-files/data-analysis/data/in"))
				.filter(path -> !path.endsWith(".dat"));

		pathStream.forEach(path -> {
			System.out.println(path);
			try {
				String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
				System.out.println(content);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}
}
