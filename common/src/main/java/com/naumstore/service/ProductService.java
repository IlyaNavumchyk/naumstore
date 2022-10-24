package com.naumstore.service;

import com.naumstore.domain.category.Category;
import com.naumstore.domain.product.Product;
import com.naumstore.exception.NoSuchEntityException;
import com.naumstore.repository.CategoryRepository;
import com.naumstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
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

    @Transactional
    public Product block(Long id, Boolean isDeleted) {

        Product product = findById(id);

        product.setIsDeleted(isDeleted);
        update(product);

        return product;
    }
}
