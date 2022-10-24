package com.naumshop.controller.converters;

import com.naumshop.controller.response.CategoryResponse;
import com.naumshop.domain.category.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryResponse convert(Category category);

    List<CategoryResponse> mapToResponse(List<Category> categories);
}
