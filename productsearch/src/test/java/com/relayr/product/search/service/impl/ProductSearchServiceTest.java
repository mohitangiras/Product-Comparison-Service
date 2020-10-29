package com.relayr.product.search.service.impl;

import com.relayr.product.search.entity.Product;
import com.relayr.product.search.entity.ProductSearchRequest;
import com.relayr.product.search.repository.ProductRepository;
import static org.mockito.ArgumentMatchers.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductSearchServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductSearchServiceImpl productSearchService;

    @Before
    public void setup() {
        List<Product> products = new LinkedList<>();
        Product first = new Product();
        first.setName("Sample1");
        first.setDescription("Sample description");
        first.setCategory("Sample Category");
        first.setId("54324e4234a4324b4324d");
        products.add(first);

        Mockito.when(productRepository.findByNameIgnoreCaseLikeOrCategoryIgnoreCaseLike(anyString(), anyString()))
                .thenReturn(products);
    }

    @Test
    public void testSearchProduct()
    {
        ProductSearchRequest request = new ProductSearchRequest();
        request.setName("Sample");
        request.setCategory("Sample");
        List<Product> products = productSearchService.search(request);
        Assert.assertEquals("Sample1", products.get(0).getName());
    }
}
