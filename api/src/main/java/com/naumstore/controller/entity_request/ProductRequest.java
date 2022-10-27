package com.naumstore.controller.entity_request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(description = "Product request")
public class ProductRequest {

    @Schema(description = "Product name", required = true, defaultValue = "name", type = "string")
    @Size(min = 5, message = "Product name length must be greater than or equals to 5")
    @Size(max = 50, message = "Product name length must be less than or equal to 50")
    @NotNull(message = "Product name must be not null")
    private String name;

    @Schema(description = "Product category id", required = true, defaultValue = "5", type = "integer")
    @Min(value = 1, message = "Product category id must be greater than zero")
    @NotNull(message = "Product category id must be not null")
    private Integer categoryId;

    @Schema(description = "Product description", defaultValue = "default", type = "string")
    private String description;

    @Schema(description = "Product price", required = true, defaultValue = "1.00", type = "double")
    @DecimalMin(value = "0.01", message = "Product price must be greater than or equal to 0.01")
    @NotNull(message = "Product price must be not null")
    private Double price;

    @Schema(description = "Product count", required = true, defaultValue = "1", type = "integer")
    @Min(value = 1, message = "Product count must be greater than or equal to " + "1")
    @NotNull(message = "Product count must be not null")
    private Integer count;
}
