package com.southsystem.dataanalysis.camel;

import com.southsystem.dataanalysis.config.AppConfiguration;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class WatchRouterBuilder extends RouteBuilder {

    private final AppConfiguration appConfiguration;
    private final DataProcessor dataProcessor;

    public WatchRouterBuilder(AppConfiguration appConfiguration, DataProcessor dataProcessor) {
        this.appConfiguration = appConfiguration;
        this.dataProcessor = dataProcessor;
    }


    @Override
    public void configure() {
        from(createEntryRoute())
                .routeId("dataProcessorRouteId")
                .log("File event: ${header.CamelFileEventType} occurred on file ${header.CamelFileName} at ${header.CamelFileLastModified}")
                .process(dataProcessor);
    }

    private String createEntryRoute() {
        StringBuilder builder = new StringBuilder();
        builder.append("file-watch:");
        builder.append(appConfiguration.getHomePath() + "/data/in");
        builder.append("?");
        builder.append("events=CREATE");
        builder.append("&");
        builder.append("antInclude=**/*.dat");
        return builder.toString();
    }
}
