package com.naumstore.controller.entity_request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "User credentials request")
public class CredentialsRequest {

    @Schema(description = "User login", required = true, defaultValue = "login", type = "string")
    @Size(min = 4, message = "User name length must be greater than or equals to 4")
    @Size(max = 20, message = "User name length must be less than or equal to 20")
    @Pattern(regexp = "^[A-Za-z]\\w{3,49}$", message = "User name length must be greater than or equal to 4 and" +
            "must be less than or equal to 50. User name must start with letter and consist of A-Z, a-z, 0-9 and _")
    @NotNull(message = "User name must be not null")
    private String login;

    @Schema(description = "User password", required = true, defaultValue = "default_password", type = "string")
    @Size(min = 8, message = "User password length must be greater than or equals to 8")
    @Size(max = 16, message = "User password length must be less than or equal to 16")
    @Pattern(regexp = "^[A-Za-z][\\w#]{7,15}$", message = "User password length must be greater than or equal to 4 and" +
            "must be less than or equal to 50. User name must start with letter and consist of A-Z, a-z, 0-9 and _#")
    @NotNull(message = "User password must be not null")
    private String password;

    @Schema(description = "User email", required = true, defaultValue = "user@gmail.com", type = "string")
    @Size(max = 100, message = "Email length must be less than or equal to 100")
    @Email(regexp = "^[A-Za-z][\\w.]+@[A-Za-z][A-Za-z0-9]+\\.[A-Za-z]{2,}$",
            message = "Email format [A-Za-z][\\w.]+@[A-Za-z][A-Za-z0-9]+\\.[A-Za-z]{2,}$")
    @NotNull(message = "User email must be not null")
    private String email;
}
