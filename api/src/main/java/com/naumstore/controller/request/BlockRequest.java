package com.naumstore.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * This request need to software remove or block an entity.
 * If the isDeleted parameter is null - temporary blocking;
 * true - software remove;
 * false - unblock.
 */
@Getter
@Setter
@Schema(description = "Block request")
public class BlockRequest {

    @Schema(description = "true - software delete, null - block, false - unblock",
            required = true, defaultValue = "false", type = "boolean")
    Boolean isDeleted;
}
