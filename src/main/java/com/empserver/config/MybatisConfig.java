package com.empserver.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

//import javax.annotation.Resources;
import javax.sql.DataSource;
import java.io.InputStream;
import java.io.Reader;

@Configuration
@AutoConfigureAfter(AppConfig.class)
@MapperScan(basePackages = {"com.empserver.mapper"} ,sqlSessionFactoryRef = "defaultSqlSessionFactory")
public class MybatisConfig {

//    @Bean("dataSourceDB")
//    DataSource dataSource(){
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://140.143.45.252:3306/employees?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
//        dataSource.setUsername("testuser");
//        dataSource.setPassword("19811981");
//        dataSource.setMaxIdle(20);
//        dataSource.setInitialSize(3);
//        dataSource.setValidationQuery("select now()");
//        dataSource.setMaxTotal(30);
//        return dataSource;
//    }
//
//    @Bean("sqlSessionFactory")
//    SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceDB") DataSource dataSource,
//                                        @Value("classpath:mappers/*.xml") Resource[] resources,
//                                        @Value("classpath:mybatis-config.xml") Resource config) throws Exception{
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(resources);
//        sqlSessionFactoryBean.setConfigLocation(config);
//        return sqlSessionFactoryBean.getObject();
//    }

    @Autowired
    //@Qualifier("writeReadDataSource")
    WriteReadRoutingDataSource dataSource;

    @Bean("defaultSqlSessionFactory")
    SqlSessionFactory sqlSessionFactory(
            //@Value("classpath:mappers/*.xml") Resource[] resources,
                                        @Value("classpath:mybatis-config.xml") Resource config) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setConfigLocation(config);
        return sqlSessionFactoryBean.getObject();
    }

//    @Bean(name = "devSqlSessionFactory")
//    @Primary
//    SqlSessionFactory devSqlSessionFactory(//@Qualifier("dataSourceDB") DataSource dataSource
//                                        //@Value("classpath:mappers/*.xml") Resource[] resources,
//                                        //@Value("classpath:mybatis-config.xml") Resources config
//    ) throws Exception{
//        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//        //sqlSessionFactory.getConfiguration().getD
//        return sqlSessionFactory;
//    }
//
//    @Bean(name = "sqlSessionTemplateDB")
//    public SqlSessionTemplate sqlSessionTemplateDB(@Qualifier("devSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

}
