package com.naumstore.controller.converter;

import com.naumstore.controller.entity_response.CategoryResponse;
import com.naumstore.domain.category.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryResponse mapToResponse(Category category);

    List<CategoryResponse> mapToResponse(List<Category> categories);
}
