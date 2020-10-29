package com.relayr.product.datasource.service;

import com.relayr.product.search.entity.Product;

import java.util.Date;
import java.util.List;

public interface DataSourceService<T> {
    /**
     * Query all the products from a datasource based on date range
     * @param from
     * @param to
     * @return {@link List} of Entity T
     */
    List<T> export(Date from, Date to);
}
