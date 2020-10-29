package com.relayr.product.search.entity;

import com.relayr.product.config.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
@EqualsAndHashCode
public class Product {
    @Id
    @EqualsAndHashCode.Exclude
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    String id;

    @Schema(description = "Product name", example = "Smart")
    @NotBlank
    String name;

    @Schema(description = "Product Description", example = "Smart appliances")
    @EqualsAndHashCode.Exclude
    String description;

    @Schema(description = "Product Category")
    @NotBlank
    String category;

    @Schema(description = "Name of the data source", example = Constants.DataSource.AMAZON)
    @NotBlank
    String dataSource;

    @Schema(description = "Boolean value to be filled by AI service")
    boolean recommended;
}
