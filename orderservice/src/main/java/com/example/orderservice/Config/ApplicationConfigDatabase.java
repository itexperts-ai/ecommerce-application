package com.example.orderservice.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfigDatabase {
    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.order")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.product")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
