package com.naumshop.service;

import com.naumshop.domain.category.Category;
import com.naumshop.domain.category.ProductCategories;
import com.naumshop.domain.product.Product;
import com.naumshop.repository.CategoryRepository;
import com.naumshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> findAllCategories() {

        return categoryRepository.findAll();
    }

    public Page<Product> findAllProductsByCategoryName(ProductCategories productCategory, int pageNumber, int pageSize) {

        Category category = categoryRepository.findByCategoryName(productCategory);

        return productRepository.findAllByCategoryAndIsDeleted(
                category, false,
                PageRequest.of(pageNumber, pageSize, Sort.by("productName"))

        );
    }
}
