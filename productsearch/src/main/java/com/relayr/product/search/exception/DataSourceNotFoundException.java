package com.relayr.product.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DataSourceNotFoundException extends ResponseStatusException {
    DataSourceNotFoundException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public static DataSourceNotFoundException dataSourceNotFound(final String datasource){
        return new DataSourceNotFoundException(datasource + " is not a valid data source");
    }
}
