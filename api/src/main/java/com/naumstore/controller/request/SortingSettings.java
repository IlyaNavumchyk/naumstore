package com.naumstore.controller.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Page sorting request")
public class SortingSettings {

    @Schema(description = "sort field", defaultValue = "price", type = "string")
    private String sortField;

    @Schema(description = "sort direction", defaultValue = "ASC", type = "string")
    private String sortDirection;
}
