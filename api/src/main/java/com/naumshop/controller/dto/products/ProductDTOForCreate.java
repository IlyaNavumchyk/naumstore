package com.naumshop.controller.dto.products;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTOForCreate {

    private String name;

    private int categoryId;

    private String description;

    private Double price;

    private int count;
}
