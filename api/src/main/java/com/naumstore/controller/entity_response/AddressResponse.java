package com.naumstore.controller.entity_response;

import lombok.Data;

@Data
public class AddressResponse {

    private String country;

    private String city;

    private String street;

    private String house;

    private String flat;
}
