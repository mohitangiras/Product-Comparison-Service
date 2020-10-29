package com.relayr.product.config;

import com.relayr.product.datasource.service.DataSourceService;
import com.relayr.product.datasource.service.impl.AmazonDataSourceServiceImpl;
import com.relayr.product.search.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Multiple data sources can be configured here
 */
@Configuration
public class DataSourceConfig {
    @Autowired
    ApplicationContext applicationContext;

    @Bean(name = Constants.DataSource.AMAZON)
    DataSourceService<Product> getAmazonService() {
        return applicationContext.getBean(AmazonDataSourceServiceImpl.class);
    }

}
