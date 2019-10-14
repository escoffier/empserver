package com.empserver;

import com.empserver.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class EmpserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmpserverApplication.class, args);
    }

}

