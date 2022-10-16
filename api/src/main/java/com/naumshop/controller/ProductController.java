package com.naumshop.controller;

import com.naumshop.controller.converters.ProductMapper;
import com.naumshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static com.naumshop.controller.DefaultResponseTag.PRODUCT;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    //private final ProductMapper mapper;


    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") String id) {

        Long productId = Long.parseLong(id);

        return new ResponseEntity<>(
                Collections.singletonMap(PRODUCT, productService.findById(productId)),
                HttpStatus.OK
        );
    }

    /*@PostMapping
    public ResponseEntity<Object> add(ProductDTOForCreate productDTO) {

        //mapper
        Product product = productMapper.mapForCreate(productDTO);

        productService.create(product);

        return new ResponseEntity<>(
                Collections.singletonMap(PRODUCT, product),
                HttpStatus.OK
        );
    }*/
}
