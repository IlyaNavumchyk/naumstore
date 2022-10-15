package com.naumshop.service;

import com.naumshop.domain.product.Product;
import com.naumshop.exception.NoSuchEntityException;
import com.naumshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public Product findById(Long id) {

        return productRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("The product with id \"%s\" was not found", id))
        );
    }

    public Product findByProductName(String name) {

        return productRepository.findByProductNameLikeIgnoreCase(name).orElseThrow(() ->
                new NoSuchEntityException(String.format("The product with this name \"%s\" was not found", name))
        );
    }

    public Set<Product> searchByProductNameOrDescription(String name) {

        return productRepository.searchByProductNameOrDescription(name).orElseThrow(() ->
                new NoSuchEntityException(String.format("The product with this name \"%s\" was not found", name))
        );
    }

    @Transactional
    public void create(Product product) {

        productRepository.save(product);
    }

    @Transactional
    public void update(Product product) {

        productRepository.save(product);
    }

    @Transactional
    public void delete(Product product) {

        product.setIsDeleted(true);

        productRepository.save(product);
    }


}
