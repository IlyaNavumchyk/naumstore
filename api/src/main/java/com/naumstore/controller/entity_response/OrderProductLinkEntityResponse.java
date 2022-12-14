package com.naumstore.controller.entity_response;

import lombok.Data;

@Data
public class OrderProductLinkEntityResponse {

    private Long productId;

    private String productName;

    private Integer productCount;

    private Double productPrice;
}
