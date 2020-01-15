package com.pollra.config.mssql;

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
    @Value("${mssql.spring.datasource.jdbcUrl}")
    private String jdbcUrl;
    @Value("${mssql.spring.datasource.username}")
    private String username;
    @Value("${mssql.spring.datasource.password}")
    private String password;
    @Value("${mssql.spring.datasource.maximumPoolSize}")
    private int maximumPoolSize;
    @Value("${mssql.spring.datasource.database}")
    private String database;
    @Value("${mssql.spring.datasource.port}")
    private int port;

    @Lazy
    @Bean(destroyMethod = "")
    public DataSource dataSource(){
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setURL(this.jdbcUrl);
        dataSource.setPortNumber(this.port);
        dataSource.setDatabaseName(this.database);
        dataSource.setUser(this.username);
        dataSource.setPassword(this.password);
        dataSource.setStatementPoolingCacheSize(this.maximumPoolSize);

        return dataSource;
    }
}
