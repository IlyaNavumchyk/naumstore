package com.naumshop.controller.dto.user;

import com.naumshop.domain.user.Gender;
import lombok.Data;

@Data
public class UserDTO {

    private CredentialsDTO credentials;

    private String name;

    private String surname;

    private String patronymic;

    private String birth;

    private Gender gender;

    private AddressDTO address;
}
