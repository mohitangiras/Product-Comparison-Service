package com.relayr.product.search.service.impl;

import com.relayr.product.config.Constants;
import com.relayr.product.datasource.repository.AmazonRepository;
import com.relayr.product.datasource.service.impl.AmazonDataSourceServiceImpl;
import com.relayr.product.search.entity.Product;
import com.relayr.product.search.entity.datasource.DataSource;
import com.relayr.product.search.entity.datasource.LoadRequest;
import com.relayr.product.search.exception.DataSourceNotFoundException;
import com.relayr.product.search.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DataServiceTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    ApplicationContext applicationContext;

    @InjectMocks
    DataServiceImpl dataService;

    @Mock
    AmazonRepository amazonRepository;

    @InjectMocks
    AmazonDataSourceServiceImpl amazonDataSourceService;

    @Before
    public void setup() {
        Product product = new Product();
        product.setName("Sample1");
        product.setDescription("Sample description");
        product.setCategory("Sample Category");
        product.setId("54324e4234a4324b4324d");

        List<Product> products = new LinkedList<>();
        products.add(product);

        Mockito.when(productRepository.findByNameAndCategoryAndDataSource(anyString(), anyString(), anyString()))
                .thenReturn(product);

        Mockito.when(amazonRepository.findProducts(any(), any()))
                .thenReturn(products);

        Mockito.when(applicationContext.getBean(Constants.DataSource.AMAZON)).thenReturn(amazonDataSourceService);

        Mockito.when(applicationContext.getBean(anyString())).thenThrow(new NoSuchBeanDefinitionException("Invalid source name"));
    }

    @Test
    public void testLoadDataValidSources() {
        LoadRequest request = new LoadRequest();
        DataSource dataSource = new DataSource();
        dataSource.setName(Constants.DataSource.AMAZON);
        request.setDataSources(Collections.singletonList(dataSource));
        int productCount = dataService.loadData(request);
        Assert.assertEquals(1, productCount);
    }

    @Test(expected = DataSourceNotFoundException.class)
    public void testLoadDataInvalidSource() {
        LoadRequest request = new LoadRequest();
        DataSource dataSource = new DataSource();
        dataSource.setName("flipkart");
        request.setDataSources(Collections.singletonList(dataSource));
        dataService.loadData(request);
    }
}
