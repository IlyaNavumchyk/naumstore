package com.naumstore.controller.entity_request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class AddressRequest {

    private String country;

    private String city;

    private String street;

    @Min(value = 1, message = "House number must be greater than or equal to 1")
    private Integer house;

    @Min(value = 1, message = "House number must be greater than or equal to 1")
    private Integer flat;
}
