package com.relayr.product.search.controller;

import com.relayr.product.search.entity.Product;
import com.relayr.product.search.entity.ProductSearchRequest;
import com.relayr.product.search.service.ProductSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class ProductSearchController {

    @Autowired
    ProductSearchService productSearchService;

    @Operation(description = "Search Product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search Success"),
            @ApiResponse(responseCode = "400", description = "Malformed search request")
    })
    @PostMapping
    public ResponseEntity<List<Product>> search(@RequestBody @Valid ProductSearchRequest productSearchRequest) {
        return ResponseEntity.ok(productSearchService.search(productSearchRequest));
    }
}
