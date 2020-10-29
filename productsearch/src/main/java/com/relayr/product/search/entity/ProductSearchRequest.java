package com.relayr.product.search.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductSearchRequest {
    @NotBlank
    @Schema(description = "Name of the product", example = "Smart")
    String name;

    @NotBlank
    @Schema(description = "Name of the category", example = "Smart Electronics")
    String category;
}
