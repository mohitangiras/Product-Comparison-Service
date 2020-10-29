package com.relayr.product.search.service;

import com.relayr.product.search.entity.datasource.LoadRequest;

public interface DataService {
    Integer loadData(LoadRequest loadRequest);
}
