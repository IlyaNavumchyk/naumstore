package com.naumshop.dto.user;

import lombok.Data;

@Data
public class CredentialsDTO {

    private String login;

    private String password;

    private String email;
}
