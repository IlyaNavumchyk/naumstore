package com.naumshop.controller.response;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer count;

    private Boolean isDeleted;

    private CategoryResponse category;
}
