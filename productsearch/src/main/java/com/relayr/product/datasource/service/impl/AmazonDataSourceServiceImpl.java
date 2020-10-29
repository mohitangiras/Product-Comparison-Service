package com.relayr.product.datasource.service.impl;

import com.relayr.product.datasource.repository.AmazonRepository;
import com.relayr.product.datasource.service.DataSourceService;
import com.relayr.product.search.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AmazonDataSourceServiceImpl implements DataSourceService<Product> {

    @Autowired
    AmazonRepository amazonRepository;

    @Override
    public List<Product> export(Date from, Date to) {
        return amazonRepository.findProducts(from, to);
    }
}
