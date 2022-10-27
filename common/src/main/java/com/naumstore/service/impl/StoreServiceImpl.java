package com.naumstore.service.impl;

import com.naumstore.domain.category.Category;
import com.naumstore.domain.category.ProductCategories;
import com.naumstore.domain.product.Product;
import com.naumstore.exception.NoSuchEntityException;
import com.naumstore.repository.CategoryRepository;
import com.naumstore.repository.ProductRepository;
import com.naumstore.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Cacheable("categories")
    public List<Category> findAllCategories() {

        return categoryRepository.findAll();
    }

    public Page<Product> findAllProductsByCategoryName(ProductCategories productCategory, Pageable pageable) {

        Category category = categoryRepository.findByCategoryName(productCategory).orElseThrow(() ->
                new NoSuchEntityException(String.format("Category with this name \"%s\" not found", productCategory)));

        return productRepository.findAllByCategoryAndIsDeleted(category, false, pageable);
    }


    public Page<Product> searchByProductNameOrDescription(String productName, Pageable pageable) {

        Page<Product> products = productRepository.searchByProductNameOrDescription(productName, pageable);

        if (products.isEmpty()) {
            throw new NoSuchEntityException(String.format("No result found for \"%s\"", productName));
        } else {
            return products;
        }
    }
}
