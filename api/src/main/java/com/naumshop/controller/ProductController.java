package com.naumshop.controller;

import com.naumshop.controller.converters.ProductMapper;
import com.naumshop.controller.dto.products.ProductDTO;
import com.naumshop.controller.request.DeleteRequest;
import com.naumshop.domain.product.Product;
import com.naumshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static com.naumshop.controller.DefaultResponseTag.PRODUCT;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper mapper;


    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") String id) {

        Long productId = Long.parseLong(id);

        return new ResponseEntity<>(
                Collections.singletonMap(PRODUCT, productService.findById(productId)),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ProductDTO productDTO) {

        Product product = mapper.mapForCreate(productDTO);

        productService.create(product, productDTO.getCategoryId());

        return new ResponseEntity<>(
                Collections.singletonMap(PRODUCT, product),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id,
                                         @RequestBody ProductDTO productDTO) {

        long productId = Long.parseLong(id);

        Product product = productService.findById(productId);

        mapper.mapForUpdate(productDTO, product);

        boolean isCategoryChanged = product.getCategory().getId() != productDTO.getCategoryId();

        if (isCategoryChanged) {
            productService.create(product, productDTO.getCategoryId());
        } else {
            productService.update(product);
        }

        return new ResponseEntity<>(
                Collections.singletonMap(PRODUCT, product),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id,
                                         @RequestBody DeleteRequest isDel) {

        long productId = Long.parseLong(id);
        Boolean isDeleted = isDel.getIsDeleted();

        Product product = productService.findById(productId);
        product.setIsDeleted(isDeleted);

        productService.update(product);

        return new ResponseEntity<>(
                Collections.singletonMap(PRODUCT, product),
                HttpStatus.OK
        );
    }
}
