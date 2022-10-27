package com.naumstore.controller.entity_request;

import lombok.Data;

import javax.validation.Valid;

@Data
public class UserRequest {

    @Valid
    private CredentialsRequest credentials;

    private String name;

    private String surname;

    private String patronymic;

    private String birth;

    private String gender;

    @Valid
    private AddressRequest address;
}
