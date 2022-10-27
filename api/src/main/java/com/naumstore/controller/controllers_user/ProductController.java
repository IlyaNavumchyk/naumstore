package com.naumstore.controller.controllers_user;

import com.naumstore.controller.DefaultResponseTag;
import com.naumstore.controller.converter.ProductMapper;
import com.naumstore.domain.product.Product;
import com.naumstore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @Operation(summary = "Find product by id", description = "Return product")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") String id) {

        Long productId = Long.parseLong(id);

        Product product = productService.findById(productId);

        return new ResponseEntity<>(
                Collections.singletonMap(DefaultResponseTag.PRODUCT, productMapper.mapToResponse(product)),
                HttpStatus.OK
        );
    }
}
