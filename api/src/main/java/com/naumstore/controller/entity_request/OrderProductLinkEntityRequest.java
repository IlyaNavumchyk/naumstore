package com.naumstore.controller.entity_request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(description = "Order request")
public class OrderProductLinkEntityRequest {

    @Schema(description = "Product id", required = true, defaultValue = "1", type = "long")
    @Min(value = 1, message = "Product ID must be greater than zero")
    @NotNull(message = "Product id must be not null")
    Long id;

    @Schema(description = "Product count", required = true, defaultValue = "1", type = "integer")
    @Min(value = 1, message = "Product count must be greater than or equal to " + "1")
    @NotNull(message = "Product count must be not null")
    Integer count;

    @Schema(description = "Product count", required = true, defaultValue = "1.00", type = "double")
    @DecimalMin(value = "0.01", message = "Product price must be greater than or equal to 0.01")
    @NotNull(message = "Product price must be not null")
    Double price;
}
