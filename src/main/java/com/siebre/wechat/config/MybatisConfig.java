package com.siebre.wechat.config;

import com.siebre.wechat.basic.db.DynamicDataSourceTransactionManager;
import com.siebre.wechat.basic.query.PagePlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by jhonelee on 2017/9/19.
 */
@Configuration
@ConditionalOnClass(SqlSessionFactoryBean.class)
@ConditionalOnBean(JdbcTemplate.class)
@MapperScan(basePackages = "com.siebre.wechat")
public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setConfiguration(this.getMybatisConfiguration());
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(this.getMapperLocations());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.siebre.wechat.**.module");
        sqlSessionFactoryBean.setPlugins(this.getPlugins());
        return sqlSessionFactoryBean;
    }

    private org.apache.ibatis.session.Configuration getMybatisConfiguration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.addMappers("com.siebre.wechat.**.mapper");
        return configuration;
    }

    private Properties getPagePluginProperties() {
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        return properties;
    }

    private Interceptor[] getPlugins() {
        PagePlugin pagePlugin = new PagePlugin();
        pagePlugin.setProperties(this.getPagePluginProperties());

        return new Interceptor[] {
                pagePlugin
        };
    }

    private Resource[] getMapperLocations() {
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            return  resourcePatternResolver.getResources("classpath*:sqlmapper/*Mapper.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public DynamicDataSourceTransactionManager transactionManager (@Autowired DataSource dataSource) {
        DynamicDataSourceTransactionManager dynamicDataSourceTransactionManager = new DynamicDataSourceTransactionManager();
        dynamicDataSourceTransactionManager.setDataSource(dataSource);
        dynamicDataSourceTransactionManager.setNestedTransactionAllowed(true);
        return dynamicDataSourceTransactionManager;
    }

}
