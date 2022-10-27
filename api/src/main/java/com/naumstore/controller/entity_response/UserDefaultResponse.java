package com.naumstore.controller.entity_response;

import com.naumstore.domain.user.Gender;
import lombok.Data;

@Data
public class UserDefaultResponse {

    private Long id;

    private CredentialsDefaultResponse credentials;

    private String name;

    private String surname;

    private String patronymic;

    private String birth;

    private Gender gender;

    private Boolean isDeleted;
}
