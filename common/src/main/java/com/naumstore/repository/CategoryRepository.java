package com.naumstore.repository;

import com.naumstore.domain.category.Category;
import com.naumstore.domain.category.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategoryName(ProductCategories category);
}
