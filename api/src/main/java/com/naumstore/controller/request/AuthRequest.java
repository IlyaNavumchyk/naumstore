package com.naumstore.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(description = "Login request")
public class AuthRequest {

    @Schema(name = " login", description = "user's login", required = true, defaultValue = "login", type = "string")
    @Size(min = 4, message = "User name length must be greater than or equals to 4")
    @Size(max = 20, message = "User name length must be less than or equal to 20")
    @Pattern(regexp = "^[A-Za-z]\\w{3,49}$", message = "User name length must be greater than or equal to 4 and" +
            "must be less than or equal to 50. User name must start with letter and consist of A-Z, a-z, 0-9 and _")
    private String login;

    @Schema(description = "user's password", required = true, defaultValue = "default_password", type = "string")
    @Size(min = 8, message = "User password length must be greater than or equals to 8")
    @Size(max = 16, message = "User password length must be less than or equal to 16")
    @Pattern(regexp = "^[A-Za-z][\\w#]{7,15}$", message = "User password length must be greater than or equal to 4 and" +
            "must be less than or equal to 50. User name must start with letter and consist of A-Z, a-z, 0-9 and _#")
    private String password;
}
