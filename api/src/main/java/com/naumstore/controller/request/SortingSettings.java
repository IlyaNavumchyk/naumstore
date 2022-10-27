package com.naumstore.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Page sorting request")
public class SortingSettings {

    @Schema(description = "Sort field. Recommend values: price, count", defaultValue = "price", type = "string")
    private String sortField;

    @Schema(description = "Sort direction. Recommend values: asc, desc", defaultValue = "ASC", type = "string")
    private String sortDirection;
}
