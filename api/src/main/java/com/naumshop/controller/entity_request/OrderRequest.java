package com.naumshop.controller.entity_request;

import com.naumshop.controller.entity_request.OrderProductLinkEntityRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    List<OrderProductLinkEntityRequest> orders;
}
