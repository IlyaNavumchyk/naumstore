package com.naumstore.controller.entity_request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductRequest {

    @Size(min = 5, message = "Product name length must be greater than or equals to 5")
    @Size(max = 50, message = "Product name length must be less than or equal to 50")
    @NotNull(message = "Product name must be not null")
    private String name;

    @Min(value = 1, message = "Category ID must be greater than zero")
    private int categoryId;

    private String description;

    @DecimalMin(value = "0.01", message = "Product price must be greater than or equal to 0.01")
    private double price;

    @Min(value = 1, message = "Product count must be greater than or equal to " + "1")
    private int count;
}
