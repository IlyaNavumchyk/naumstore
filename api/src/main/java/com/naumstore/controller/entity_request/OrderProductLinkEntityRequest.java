package com.naumstore.controller.entity_request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@Getter
@Setter
public class OrderProductLinkEntityRequest {

    @Min(value = 1, message = "Product ID must be greater than zero")
    long id;

    @Min(value = 1, message = "Product count must be greater than or equal to " + "1")
    int count;

    @DecimalMin(value = "0.01", message = "Product price must be greater than or equal to 0.01")
    double price;
}
