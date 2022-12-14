package com.naumstore.repository;

import com.naumstore.domain.category.Category;
import com.naumstore.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findAllByName(String name);

    Page<Product> findAllByCategoryAndIsDeleted(Category category, Boolean bool, Pageable pageable);

    @Query(value = "select * from store.products " +
            "where (product_name ilike '%'||:name||'%' or description ilike '%'||:name||'%') " +
            "and is_deleted = false", nativeQuery = true)
    Page<Product> searchByProductNameOrDescription(@Param("name") String name, Pageable pageable);
}