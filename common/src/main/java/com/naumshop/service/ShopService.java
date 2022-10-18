package com.naumshop.service;

import com.naumshop.domain.category.Category;
import com.naumshop.domain.category.ProductCategories;
import com.naumshop.domain.product.Product;
import com.naumshop.exception.NoSuchEntityException;
import com.naumshop.repository.CategoryRepository;
import com.naumshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Cacheable("categories")
    public List<Category> findAllCategories() {

        return categoryRepository.findAll();
    }

    public Page<Product> findAllProductsByCategoryName(ProductCategories productCategory, Pageable pageable) {

        Category category = categoryRepository.findByCategoryName(productCategory);

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
