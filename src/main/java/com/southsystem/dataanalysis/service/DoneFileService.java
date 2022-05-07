package com.southsystem.dataanalysis.service;

import com.southsystem.dataanalysis.config.AppConfiguration;
import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.core.model.Report;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DoneFileService {

    Logger logger = LoggerFactory.getLogger(DoneFileService.class);

    private final WriteFileService fileWriterService;
    private final AppConfiguration appConfiguration;

    public DoneFileService(WriteFileService fileWriterService, AppConfiguration appConfiguration) {
        this.fileWriterService = fileWriterService;
        this.appConfiguration = appConfiguration;
    }


    public void createReport(FileData fileData, Path path) {
        Report finalReport = Report.builder()
                .numberOfClients(fileData.getClients().size())
                .numberOfSalesMan(fileData.getSales().size())
                .idHigherSale(fileData.getHigherSaleId())
                .worstSalesMan(fileData.getWorstSalesMan())
                .build();

        String doneFilePath = createDoneFilePath(path);

        try {
            logger.info(String.format("Creating final report %s", path.getFileName()));
            fileWriterService.writeFile(Paths.get(doneFilePath), finalReport.toString());
        } catch (IOException e) {
            logger.error("Failed creating final report", e);
        }
    }

    private String createDoneFilePath(Path path) {
        StringBuilder sb = new StringBuilder();
        sb.append(appConfiguration.getHomePath());
        sb.append("/data/out/");
        sb.append(FilenameUtils.removeExtension(path.getFileName().toString()));
        sb.append(".done.dat");
        return sb.toString();
    }
}
