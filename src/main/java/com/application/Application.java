package com.application;

import com.application.tools.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({FileStorageProperties.class})
@SpringBootApplication
public class Application{

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(com.application.Application.class, args);
    }
}



