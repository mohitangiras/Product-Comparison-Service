package com.relayr.product.search.entity.datasource;

import com.relayr.product.config.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class DataSource {
    @NotBlank
    @Schema(description = "Name of the data source", example = Constants.DataSource.AMAZON)
    String name;

    @Schema(description = "Optional version for data source", example = "")
    String version;

    @Schema(example = "")
    Date from;

    @Schema(example = "")
    Date to;
}
