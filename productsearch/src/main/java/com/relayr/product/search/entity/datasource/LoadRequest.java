package com.relayr.product.search.entity.datasource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class LoadRequest {
    @Size(min = 1, message = "Minimum 1 datasource is required")
    @Schema(description = "Minimum one data source is required")
    List<DataSource> dataSources;
}
