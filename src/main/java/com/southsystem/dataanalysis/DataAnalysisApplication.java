package com.southsystem.dataanalysis;

import com.southsystem.dataanalysis.config.AppConfiguration;
import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.service.DoneFileService;
import com.southsystem.dataanalysis.service.EntryFileService;
import com.southsystem.dataanalysis.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
public class DataAnalysisApplication {


	@Autowired
	private ReadFileService readFileService;

	@Autowired
	private AppConfiguration appConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(DataAnalysisApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws IOException {
		System.out.println("hello world, I have just started up");

		String content = readFileService.readFile(Paths.get("/" + appConfiguration.getHomePath() + "/data/in/sample.dat"));
		System.out.println(content);
	}
}
