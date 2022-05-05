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

        StringBuilder builder = new StringBuilder();
        builder.append("file-watch:");
        builder.append("Users/alexa/data/in");
        builder.append("?");
        builder.append("events=CREATE");
        builder.append("&");
        builder.append("antInclude=**/*.dat");


/*        System.out.println("passeui aqui");
        System.out.println(appConfiguration.getHomePath());
        System.out.println(String.format("%s/data/in", appConfiguration.getHomePath()));*/

        from(builder.toString())
                .routeId("dataProcessorRouteId")
                .process(dataProcessor);

    }
}
