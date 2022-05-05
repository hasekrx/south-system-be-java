package com.southsystem.dataanalysis.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfiguration {

    @Value("${home-path}")
    private String homePath;
}
