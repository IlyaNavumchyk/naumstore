package com.naumstore.controller.entity_request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "User address request")
public class AddressRequest {

    @Schema(description = "User country", defaultValue = "country", type = "string")
    @Size(max = 50, message = "Country name length must be less than or equal to 50")
    @Pattern(regexp = "^[A-Za-z][\\w ]$", message = "Country name must start with letter " +
            "and consist of A-Z, a-z, 0-9 and _ ")
    private String country;

    @Schema(description = "User city", defaultValue = "city", type = "string")
    @Size(max = 50, message = "City name length must be less than or equal to 50")
    @Pattern(regexp = "^[A-Za-z][\\w ]$", message = "City name must start with letter " +
            "and consist of A-Z, a-z, 0-9 and _ ")
    private String city;

    @Schema(description = "User street", defaultValue = "street", type = "string")
    @Size(max = 100, message = "Street name length must be less than or equal to 100")
    @Pattern(regexp = "^[A-Za-z][\\w ]$", message = "Street name must start with letter " +
            "and consist of A-Z, a-z, 0-9 and _ ")
    private String street;

    @Schema(description = "User house", defaultValue = "1", type = "integer")
    @Min(value = 1, message = "House number must be greater than or equal to 1")
    private Integer house;

    @Schema(description = "User flat", defaultValue = "1", type = "integer")
    @Min(value = 1, message = "Flat number must be greater than or equal to 1")
    private Integer flat;
}
