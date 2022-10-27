package com.naumstore.controller.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Page settings request")
public class PageSettings {

    @Schema(description = "page number", defaultValue = "1", type = "string")
    private String page;

    @Schema(description = "page size", defaultValue = "10", type = "string")
    private String size;

}
