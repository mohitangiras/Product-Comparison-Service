package com.relayr.product.datasource.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relayr.product.datasource.entity.AmazonProduct;
import com.relayr.product.search.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dummy repository to load the data from json file
 */
@Component
@Slf4j
public class AmazonRepository {
    public List<Product> findProducts(Date from, Date to) {
        InputStream productStream = getClass().getClassLoader().getResourceAsStream("amazon-product.json");

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = new LinkedList<>();
        try {
            AmazonProduct[] amazonProductArray = objectMapper.readValue(productStream, AmazonProduct[].class);

            ModelMapper modelMapper = new ModelMapper();
            products = Arrays.stream(amazonProductArray).map(amazonProduct -> modelMapper.map(amazonProduct, Product.class))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            log.error("Unable to load data from Amazon repository");
        }
        return products;
    }
}
