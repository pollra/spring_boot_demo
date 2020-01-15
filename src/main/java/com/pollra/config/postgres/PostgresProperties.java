package com.pollra.config.postgres;

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
public class PostgresProperties {
    @Value("${postgres.spring.datasource.jdbcUrl}")
    private String jdbcUrl;
    @Value("${postgres.spring.datasource.username}")
    private String username;
    @Value("${postgres.spring.datasource.password}")
    private String password;
    @Value("${postgres.spring.datasource.maximumPoolSize}")
    private int maximumPoolSize;
    @Value("${postgres.spring.datasource.auto-commit}")
    private boolean autoCommit;

    @Lazy
    @Bean(destroyMethod = "")
    public DataSource dataSource(){
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maximumPoolSize);
        hikariConfig.setAutoCommit(autoCommit);

        hikariConfig.setLeakDetectionThreshold(2000);
        hikariConfig.setPoolName("spring_boot_demo");

        final HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }
}
