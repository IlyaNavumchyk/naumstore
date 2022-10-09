package com.naumshop.dto.user;

import lombok.Data;

@Data
public class AddressDTO {

    private String country;

    private String city;

    private String street;

    private Integer house;

    private Integer flat;
}
