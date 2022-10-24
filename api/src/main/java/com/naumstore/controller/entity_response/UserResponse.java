package com.naumstore.controller.entity_response;

import com.naumstore.domain.user.Gender;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {

    private Long id;

    private CredentialsResponse credentials;

    private String name;

    private String surname;

    private String patronymic;

    private String birth;

    private Gender gender;

    private Boolean isDeleted;

    private AddressResponse address;

    private Set<RoleResponse> roles;

    private Set<OrderResponse> orders;
}
