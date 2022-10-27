package com.naumstore.service.impl;

import com.naumstore.domain.category.Category;
import com.naumstore.domain.product.Product;
import com.naumstore.exception.EntityAlreadyExsistException;
import com.naumstore.exception.NoSuchEntityException;
import com.naumstore.repository.CategoryRepository;
import com.naumstore.repository.ProductRepository;
import com.naumstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public Product findById(Long id) {

        return productRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("The product with id \"%s\" was not found", id))
        );
    }

    @Transactional
    public void create(Product product, int productCategoryId) {

        if (checkProductNameForNotExistInDB(product)) {

            Category category = categoryRepository.findById(productCategoryId).orElseThrow(() ->
                    new NoSuchEntityException(String.format("Category with this id \"%d\" not found", productCategoryId)));

            product.setCategory(category);

            productRepository.save(product);
        }
    }

    @Transactional
    public void update(Product product) {

        if (checkProductNameForNotExistInDB(product)) {

            productRepository.save(product);
        }
    }

    @Transactional
    public Product block(Long id, Boolean isDeleted) {

        Product product = findById(id);

        product.setIsDeleted(isDeleted);
        update(product);

        return product;
    }

    private boolean checkProductNameForNotExistInDB(Product product) {

        String productName = product.getName();
        Optional<Product> productByName = productRepository.findAllByName(productName);

        if (productByName.isPresent() && checkProductsIdForMismatch(productByName.get(), product)) {
            throw new EntityAlreadyExsistException(
                    String.format("Product with this name \"%s\" already exists", productName));
        }

        return true;
    }

    private boolean checkProductsIdForMismatch(Product product1, Product product2) {

        return !product1.getId().equals(product2.getId());
    }
}
