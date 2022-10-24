package com.naumshop.controller.entity_request;

import lombok.Data;

@Data
public class AddressRequest {

    private String country;

    private String city;

    private String street;

    private Integer house;

    private Integer flat;
}
