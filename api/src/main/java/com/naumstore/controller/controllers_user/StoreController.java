package com.naumstore.controller.controllers_user;

import com.naumstore.controller.converter.CategoryMapper;
import com.naumstore.controller.converter.ProductMapper;
import com.naumstore.controller.request.PageSettings;
import com.naumstore.controller.request.SortFields;
import com.naumstore.controller.request.SortingSettings;
import com.naumstore.domain.category.Category;
import com.naumstore.domain.category.ProductCategories;
import com.naumstore.domain.product.Product;
import com.naumstore.exception.NoSuchEntityException;
import com.naumstore.service.StoreService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import java.util.List;

import static com.naumstore.controller.DefaultResponseTag.PRODUCTS;
import static com.naumstore.controller.DefaultResponseTag.RESULT;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_SORT_FIELD = "price";

    private final StoreService shopService;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<Object> findAllCategories() {

        List<Category> allCategories = shopService.findAllCategories();

        return new ResponseEntity<>(
                Collections.singletonMap(RESULT, categoryMapper.mapToResponse(allCategories)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{category}")
    public ResponseEntity<Object> findAllProductsByCategoryName(@PathVariable("category") @Schema(defaultValue = "HOME_APPLIANCES") String categoryName,
                                                                @ModelAttribute @Parameter(description = "page param") PageSettings pageSettings,
                                                                @ModelAttribute @Parameter(description = "sorting param") SortingSettings sortingSettings) {

        ProductCategories productCategory;

        try {
            productCategory = ProductCategories.valueOf(categoryName.toUpperCase());
        } catch (Exception e) {
            throw new NoSuchEntityException(String.format("This category \"%s\" was not found", categoryName));
        }

        Pageable pageable = getPageable(pageSettings, sortingSettings);

        Page<Product> page = shopService.findAllProductsByCategoryName(productCategory, pageable);

        return new ResponseEntity<>(Collections.singletonMap(PRODUCTS,
                page.map(productMapper::mapToResponse)),
                HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchByProductName(@ModelAttribute("q") String productName,
                                                      @ModelAttribute PageSettings pageSettings,
                                                      @ModelAttribute SortingSettings sortingSettings) {

        Pageable pageable = getPageable(pageSettings, sortingSettings);

        Page<Product> page = shopService.searchByProductNameOrDescription(productName, pageable);

        return new ResponseEntity<>(
                page.map(productMapper::mapToResponse),
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
        try {
            SortFields.valueOf(sortField.toUpperCase());
        } catch (Exception e) {
            sortField = DEFAULT_SORT_FIELD;
        }

        try {
            sortDirection = Sort.Direction.valueOf(sortingSettings.getSortDirection().toUpperCase());
        } catch (Exception e) {
            sortDirection = Sort.DEFAULT_DIRECTION;
        }

        System.out.println("" + pageNumber + pageSize + sortDirection + sortField);

        return PageRequest.of(pageNumber, pageSize, sortDirection, sortField);
    }
}
