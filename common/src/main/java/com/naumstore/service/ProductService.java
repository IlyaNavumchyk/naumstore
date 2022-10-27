package com.naumstore.service;

import com.naumstore.domain.product.Product;

public interface ProductService {

    Product findById(Long id);

    void create(Product product, int productCategoryId);

    void update(Product product);

    Product block(Long id, Boolean isDeleted);
}
