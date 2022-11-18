package com.naumstore.controller.controllers_admin;

import com.naumstore.controller.DefaultResponseTag;
import com.naumstore.controller.converter.ProductMapper;
import com.naumstore.controller.entity_request.ProductRequest;
import com.naumstore.controller.request.BlockRequest;
import com.naumstore.domain.product.Product;
import com.naumstore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class AdminProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @PostMapping
    @Operation(summary = "Create product", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    public ResponseEntity<Object> add(@RequestBody @Valid ProductRequest productRequest) {

        Product product = productMapper.mapForCreate(productRequest);

        productService.create(product, productRequest.getCategoryId());

        return new ResponseEntity<>(
                Collections.singletonMap(DefaultResponseTag.PRODUCT, productMapper.mapToResponse(product)),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    public ResponseEntity<Object> update(@PathVariable("id") String id,
                                         @RequestBody @Valid ProductRequest productRequest) {

        long productId = Long.parseLong(id);

        Product product = productService.findById(productId);

        productMapper.mapForUpdate(productRequest, product);

        boolean isCategoryChanged = product.getCategory().getId() != productRequest.getCategoryId();

        if (isCategoryChanged) {
            productService.create(product, productRequest.getCategoryId());
        } else {
            productService.update(product);
        }

        return new ResponseEntity<>(
                Collections.singletonMap(DefaultResponseTag.PRODUCT, productMapper.mapToResponse(product)),
                HttpStatus.OK
        );
    }

    /**
     * This method need to blocks or software remove the product.
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Block product", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    public ResponseEntity<Object> block(@PathVariable("id") String id,
                                        @RequestBody BlockRequest request) {

        long productId = Long.parseLong(id);
        Boolean isDeleted = request.getIsDeleted();

        Product product = productService.block(productId, isDeleted);

        return new ResponseEntity<>(
                Collections.singletonMap(DefaultResponseTag.PRODUCT, productMapper.mapToResponse(product)),
                HttpStatus.OK
        );
    }
}
