package com.naumstore.controller.entity_request;

import com.naumstore.domain.user.Gender;
import lombok.Data;

@Data
public class UserRequest {

    private CredentialsRequest credentials;

    private String name;

    private String surname;

    private String patronymic;

    private String birth;

    private Gender gender;

    private AddressRequest address;
}
