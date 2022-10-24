package com.naumstore.controller.entity_request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    private String name;

    private int categoryId;

    private String description;

    private double price;

    private int count;
}
