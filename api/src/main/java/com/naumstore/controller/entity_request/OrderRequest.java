package com.naumstore.controller.entity_request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    List<OrderProductLinkEntityRequest> orders;
}
