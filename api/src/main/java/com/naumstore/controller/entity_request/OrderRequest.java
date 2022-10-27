package com.naumstore.controller.entity_request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Schema(description = "Product list of order request")
public class OrderRequest {

    @Schema(description = "Product id", required = true)
    @Valid
    @NotNull(message = "Product list or order must be not null")
    List<OrderProductLinkEntityRequest> orders;
}
