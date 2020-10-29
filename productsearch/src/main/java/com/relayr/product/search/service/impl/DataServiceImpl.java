package com.relayr.product.search.service.impl;

import com.relayr.product.datasource.service.DataSourceService;
import com.relayr.product.search.entity.Product;
import com.relayr.product.search.entity.datasource.DataSource;
import com.relayr.product.search.entity.datasource.LoadRequest;
import com.relayr.product.search.exception.DataSourceNotFoundException;
import com.relayr.product.search.repository.ProductRepository;
import com.relayr.product.search.service.DataService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
public class DataServiceImpl implements DataService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ApplicationContext applicationContext;

    public Integer loadData(LoadRequest loadRequest) {
        List<DataSource> dataSources = loadRequest.getDataSources();
        Integer totalProducts = 0;
        if(Objects.nonNull(dataSources) && dataSources.size() > 0) {
            for(DataSource dataSource:dataSources){
                totalProducts = totalProducts + connectAndStore(dataSource);
            };
        }
        return totalProducts;
    }

    /**
     * Fetch data from the linked data source and store it in local database
     * @param dataSource - Name identifying the datasource
     * @return
     */
    private Integer connectAndStore(DataSource dataSource) {
        Integer productsUpdated = 0;
        String name = dataSource.getName();
        if(Objects.nonNull(name)) {
            try{
                DataSourceService<Product> sourceService = (DataSourceService<Product>) applicationContext.getBean(name
                        + ((Objects.nonNull(dataSource.getVersion()))? dataSource.getVersion(): ""));
                List<Product> products = sourceService.export(dataSource.getFrom(), dataSource.getTo());
                if(Objects.nonNull(products) && products.size() > 0){
                    List<Product> existingProductList = new LinkedList<>();
                    for(Product product: products) {
                        Product existingProduct = productRepository.findByNameAndCategoryAndDataSource(product.getName(),
                                product.getCategory(), product.getDataSource());
                        if(Objects.nonNull(existingProduct)) {
                            existingProduct.setDescription(product.getDescription());

                            existingProductList.add(existingProduct);
                        } else {
                            product.setDataSource(name);
                        }
                    };

                    products.removeAll(existingProductList);
                    productRepository.saveAll(existingProductList);
                    productRepository.saveAll(products);
                    productsUpdated = existingProductList.size() + products.size();
                }
            } catch (BeansException | ClassCastException e) {
                throw DataSourceNotFoundException.dataSourceNotFound(name);
            }
        } else {
            throw DataSourceNotFoundException.dataSourceNotFound(name);
        }
        return productsUpdated;
    }
}
