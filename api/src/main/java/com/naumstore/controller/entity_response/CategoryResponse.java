package com.naumstore.controller.entity_response;

import com.naumstore.domain.category.ProductCategories;
import lombok.Data;

@Data
public class CategoryResponse {

    private Integer id;

    private ProductCategories categoryName;
}
