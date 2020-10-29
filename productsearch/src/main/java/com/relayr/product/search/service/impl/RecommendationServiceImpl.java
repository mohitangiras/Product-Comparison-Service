package com.relayr.product.search.service.impl;

import com.relayr.product.search.entity.Product;
import com.relayr.product.search.service.RecommendationService;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public void updateRecommendations(List<Product> products) {
        if(Objects.nonNull(products) && products.size() > 0) {
            int productCount = products.size();
            int selectedProduct = (int)Math.random() * (productCount - 1); //-1 for zero based index
            products.get(selectedProduct).setRecommended(true);
        }
    }
}
