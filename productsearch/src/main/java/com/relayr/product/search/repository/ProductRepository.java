package com.relayr.product.search.repository;

import com.relayr.product.search.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByNameIgnoreCaseLikeOrCategoryIgnoreCaseLike(String name, String category);
    Product findByNameAndCategoryAndDataSource(String name, String category, String dataSource);
}
