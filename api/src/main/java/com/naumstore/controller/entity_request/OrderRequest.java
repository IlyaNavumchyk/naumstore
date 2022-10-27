package com.naumstore.controller.entity_request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    @Valid
    @NotNull
    List<OrderProductLinkEntityRequest> orders;
}
