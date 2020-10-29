package com.relayr.product.search.controller;

import com.relayr.product.search.entity.datasource.LoadRequest;
import com.relayr.product.search.entity.datasource.LoadResponse;
import com.relayr.product.search.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/data")
public class DataController {

    @Autowired
    DataService dataService;

    @Operation(description = "Load data from data sources")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Data Load Success"),
            @ApiResponse(responseCode = "400", description = "Malformed search request")
    })
    @PostMapping
    public ResponseEntity<LoadResponse> load(@RequestBody @Valid LoadRequest loadRequest) {
        //Each Data Source could be treated as a separate service as well for better decoupling
        Integer products = dataService.loadData(loadRequest);
        LoadResponse loadResponse = new LoadResponse();
        loadResponse.setProductUpdated(products);
        return ResponseEntity.status(HttpStatus.OK).body(loadResponse);
    }
}
