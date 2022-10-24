package com.naumstore.controller.entity_request;

import lombok.Data;

@Data
public class CredentialsRequest {

    private String login;

    private String password;

    private String email;
}
