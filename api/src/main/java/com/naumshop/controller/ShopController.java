package com.naumshop.controller;

import com.naumshop.controller.request.PageSettings;
import com.naumshop.controller.request.SortingSettings;
import com.naumshop.domain.category.ProductCategories;
import com.naumshop.domain.product.Product;
import com.naumshop.exception.NoSuchEntityException;
import com.naumshop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static com.naumshop.controller.DefaultResponseTag.PRODUCTS;
import static com.naumshop.controller.DefaultResponseTag.RESULT;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private static final int DEFAULT_PAGE_NUMBER = 0;

    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final String DEFAULT_SORT_FIELD = "price";

    private final ShopService shopService;

    @GetMapping
    public ResponseEntity<Object> findAllCategories() {

        return new ResponseEntity<>(
                Collections.singletonMap(RESULT, shopService.findAllCategories()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{category}")
    public ResponseEntity<Object> findAllProductsByCategoryName(@PathVariable("category") String categoryName,
                                                                @ModelAttribute PageSettings pageSettings,
                                                                @ModelAttribute SortingSettings sortingSettings) {

        ProductCategories productCategory;

        try {
            productCategory = ProductCategories.valueOf(categoryName.toUpperCase());
        } catch (Exception e) {
            throw new NoSuchEntityException(String.format("This category \"%s\" was not found", categoryName));
        }

        Pageable pageable = getPageable(pageSettings, sortingSettings);

        return new ResponseEntity<>(Collections.singletonMap(PRODUCTS,
                shopService.findAllProductsByCategoryName(productCategory, pageable)),
                HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchByProductName(@ModelAttribute("q") String productName,
                                                      @ModelAttribute PageSettings pageSettings,
                                                      @ModelAttribute SortingSettings sortingSettings) {

        Pageable pageable = getPageable(pageSettings, sortingSettings);

        return new ResponseEntity<>(
                Collections.singletonMap(PRODUCTS, shopService.searchByProductNameOrDescription(productName, pageable)),
                HttpStatus.OK
        );
    }

    private Pageable getPageable(PageSettings pageSettings, SortingSettings sortingSettings) {

        int pageNumber;
        int pageSize;

        String sortField;
        Sort.Direction sortDirection;

        try {
            pageNumber = Integer.parseInt(pageSettings.getPage());
            pageNumber--;
            if (pageNumber < 0) {
                throw new IllegalArgumentException("Page index must not be less than one");
            }
        } catch (Exception e) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        try {
            pageSize = Integer.parseInt(pageSettings.getSize());
            if (pageSize < 1) {
                throw new IllegalArgumentException("Page size must not be less than one");
            }
        } catch (Exception e) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        sortField = sortingSettings.getSortField();
        if (!checkField(sortField)) {
            sortField = DEFAULT_SORT_FIELD;
        }

        try {
            sortDirection = Sort.Direction.valueOf(sortingSettings.getSortDirection().toUpperCase());
        } catch (Exception e) {
            sortDirection = Sort.DEFAULT_DIRECTION;
        }

        return PageRequest.of(pageNumber, pageSize, sortDirection, sortField);
    }

    private boolean checkField(String fieldName) {

        try {
            Product.class.getDeclaredField(fieldName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
