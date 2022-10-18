package com.naumshop.service;

import com.naumshop.domain.category.Category;
import com.naumshop.domain.product.Product;
import com.naumshop.exception.NoSuchEntityException;
import com.naumshop.repository.CategoryRepository;
import com.naumshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public Product findById(Long id) {

        return productRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("The product with id \"%s\" was not found", id))
        );
    }

    @Transactional
    public void create(Product product, int productCategoryId) {

        Category category = categoryRepository.findById(productCategoryId).orElseThrow(() ->
                new NoSuchEntityException(String.format("Category with this id \"%d\" not found", productCategoryId)));

        product.setCategory(category);

        productRepository.save(product);
    }

    @Transactional
    public void update(Product product) {

        productRepository.save(product);
    }
}
