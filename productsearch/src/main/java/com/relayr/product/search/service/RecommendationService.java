package com.relayr.product.search.service;

import com.relayr.product.search.entity.Product;

import java.util.List;

public interface RecommendationService {
    void updateRecommendations(List<Product> products);
}
