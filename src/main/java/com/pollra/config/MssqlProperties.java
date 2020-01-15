package com.pollra.config;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("application.yaml")
public class MssqlProperties {
    @Value("${spring.datasource.jdbcUrl}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.maximumPoolSize}")
    private int maximumPoolSize;
    @Value("${spring.datasource.auto-commit}")
    private boolean autoCommit;
    @Value("${spring.datasource.port}")
    private int port;
    @Value("${spring.datasource.database}")
    private String database;

    @Lazy
    @Bean(destroyMethod = "")
    public DataSource dataSource(){
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setUser(this.username);
        dataSource.setPassword(this.password);
        dataSource.setURL(this.jdbcUrl);
        dataSource.setPortNumber(this.port);
        dataSource.setDatabaseName(this.database);
        dataSource.setStatementPoolingCacheSize(this.maximumPoolSize);
        return dataSource;
    }
}

