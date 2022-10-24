package com.naumshop.controller.response;

import com.naumshop.domain.category.ProductCategories;
import lombok.Data;

@Data
public class CategoryResponse {

    private Integer id;

    private ProductCategories categoryName;
}
