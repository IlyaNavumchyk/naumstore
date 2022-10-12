package com.naumshop.controller;

import com.naumshop.controller.request.PageSettings;
import com.naumshop.domain.category.ProductCategories;
import com.naumshop.exception.NoSuchEntityException;
import com.naumshop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class ShopController {

    private static final int DEFAULT_PAGE_NUMBER = 0;

    private static final int DEFAULT_PAGE_SIZE = 1;

    private final ShopService shopService;

    @GetMapping
    public ResponseEntity<Object> findAll() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", shopService.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{category}")
    public ResponseEntity<Object> findByCategory(@PathVariable("category") String categoryName,
                                                 @ModelAttribute PageSettings pageSettings) {

        ProductCategories productCategory;
        int pageNumber;
        int pageSize;

        try {
            productCategory = ProductCategories.valueOf(categoryName.toUpperCase());
        } catch (Exception e) {
            throw new NoSuchEntityException(String.format("This category \"%s\" was not found", categoryName));
        }

        String temp = pageSettings.getPage();
        if (temp != null) {
            pageNumber = Integer.parseInt(temp);
        } else {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        temp = pageSettings.getSize();
        if (temp != null) {
            pageSize = Integer.parseInt(temp);
        } else {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return new ResponseEntity<>(
                Collections.singletonMap("result", shopService.findByCategory(productCategory, pageNumber, pageSize)),
                HttpStatus.OK
        );
    }
}
