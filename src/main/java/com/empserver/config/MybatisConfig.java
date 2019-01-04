package com.empserver.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.empserver.mapper"})
public class MybatisConfig {
    @Bean("dataSourceDB")
    DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.21.225:3306/employees?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dataSource.setUsername("testuser");
        dataSource.setPassword("19811981");
        dataSource.setMaxIdle(20);
        dataSource.setInitialSize(3);
        dataSource.setValidationQuery("select now()");
        dataSource.setMaxTotal(30);
        return dataSource;
    }

    @Bean
    SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceDB") DataSource dataSource,
                                        @Value("classpath:mappers/*.xml") Resource[] resources) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean.getObject();
    }

}
