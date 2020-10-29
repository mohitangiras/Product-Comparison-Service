package com.relayr.product.search.entity.datasource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoadResponse {
    @Schema(description = "Number of updated products")
    Integer productUpdated;
}
