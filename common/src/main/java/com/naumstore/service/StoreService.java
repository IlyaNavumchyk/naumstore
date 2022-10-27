package com.naumstore.service;

import com.naumstore.domain.category.Category;
import com.naumstore.domain.category.ProductCategories;
import com.naumstore.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {

    List<Category> findAllCategories();

    Page<Product> findAllProductsByCategoryName(ProductCategories productCategory, Pageable pageable);

    Page<Product> searchByProductNameOrDescription(String productName, Pageable pageable);
}
