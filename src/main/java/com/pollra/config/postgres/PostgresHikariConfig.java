package com.pollra.config.postgres;

import com.zaxxer.hikari.HikariConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = {"com.pollra.persistence", "com.pollra.test"},
        sqlSessionFactoryRef = "Mybatis_Postgresql_SqlSessionFactory",
        sqlSessionTemplateRef = "Mybatis_Postgresql_SqlSessionTemplate"
)
@Import({PostgresProperties.class})
public class PostgresHikariConfig extends HikariConfig {
    @Autowired
    private PostgresProperties properties;

    @Bean( name = "postgresqlDataSource" )
    public DataSource getDataSource() {
        return properties.dataSource();
    }

    @Bean( name = "Mybatis_Postgresql_SqlSessionFactory" )
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(getDataSource());
        return bean.getObject();
    }

    @Bean( name = "Mybatis_Postgresql_SqlSessionTemplate" )
    public SqlSessionTemplate getSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(getSqlSessionFactory());
    }
}
