package com.naumshop.controller.dto.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private String name;

    private int categoryId;

    private String description;

    private Double price;

    private int count;
}
