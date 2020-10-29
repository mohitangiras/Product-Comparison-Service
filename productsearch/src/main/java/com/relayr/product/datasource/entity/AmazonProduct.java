package com.relayr.product.datasource.entity;

import com.relayr.product.config.Constants;
import lombok.Data;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class AmazonProduct {
    String name;
    String description;
    String category;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date lastUpdated;

    String dataSource = Constants.DataSource.AMAZON;
}
