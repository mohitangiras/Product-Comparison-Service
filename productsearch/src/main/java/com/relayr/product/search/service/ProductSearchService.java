package com.relayr.product.search.service;

import com.relayr.product.search.entity.Product;
import com.relayr.product.search.entity.ProductSearchRequest;

import java.util.List;

public interface ProductSearchService {
    List<Product> search(ProductSearchRequest productSearchRequest);
}
