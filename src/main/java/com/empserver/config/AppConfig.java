package com.empserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement(order = 10)
public class AppConfig {

    @Bean
    DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(writeReadDataSource());
        //dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Primary
    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "app.write.datasource")
    DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "app.read.datasource")
    DataSource readDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "writeReadDataSource")
    //@Qualifier("writeReadDataSource")
    public WriteReadRoutingDataSource writeReadDataSource() {
        WriteReadRoutingDataSource routingDataSource = new WriteReadRoutingDataSource();

        Map<Object, Object> dataSources = new HashMap<>(2);
        dataSources.put(WriteReadRoutingDataSource.DataSourceMode.write, writeDataSource());
        dataSources.put(WriteReadRoutingDataSource.DataSourceMode.read, readDataSource());

        routingDataSource.setDefaultTargetDataSource(writeDataSource());
        routingDataSource.setTargetDataSources(dataSources);
        return routingDataSource;
    }
}
