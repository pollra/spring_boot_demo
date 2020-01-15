package com.pollra.config.mssql;

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
        basePackages = {"com.pollra.persistence.mssql", "com.pollra.test.mssql"},
        sqlSessionFactoryRef = "Mybatis_Mssql_SqlSessionFactory",
        sqlSessionTemplateRef = "Mybatis_Mssql_SqlSessionTemplate"
)
@Import({MssqlProperties.class})
public class MssqlHikariConfig extends HikariConfig {
    @Autowired
    private MssqlProperties properties;

    @Bean( name = "mssqlDataSource" )
    public DataSource getDataSource() {
        return properties.dataSource();
    }

    @Bean( name = "Mybatis_Mssql_SqlSessionFactory" )
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(getDataSource());
        return bean.getObject();
    }

    @Bean( name = "Mybatis_Mssql_SqlSessionTemplate" )
    public SqlSessionTemplate getSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(getSqlSessionFactory());
    }
}
