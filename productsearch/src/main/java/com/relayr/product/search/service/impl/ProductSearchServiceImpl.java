package com.relayr.product.search.service.impl;

import com.relayr.product.search.entity.Product;
import com.relayr.product.search.entity.ProductSearchRequest;
import com.relayr.product.search.repository.ProductRepository;
import com.relayr.product.search.service.ProductSearchService;
import com.relayr.product.search.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSearchServiceImpl implements ProductSearchService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    RecommendationService recommendationService;

    public List<Product> search(ProductSearchRequest productSearchRequest) {
        List<Product> products = productRepository.findByNameIgnoreCaseLikeOrCategoryIgnoreCaseLike(productSearchRequest.getName()
                ,productSearchRequest.getCategory());
        recommendationService.updateRecommendations(products);
        return products;
    }
}
