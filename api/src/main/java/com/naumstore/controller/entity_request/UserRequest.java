package com.naumstore.controller.entity_request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "User request")
public class UserRequest {

    @Schema(description = "User credentials", required = true)
    @Valid
    @NotNull(message = "User credentials must not be null")
    private CredentialsRequest credentials;

    @Schema(description = "User name", defaultValue = "name", type = "string")
    @Size(min = 3, message = "User name length must be greater than or equals to 3")
    @Size(max = 50, message = "User name length must be less than or equal to 50")
    @Pattern(regexp = "^[A-Za-z][\\w ]{2,49}$", message = "User name length must be " +
            "greater than or equal to 4 and must be less than or equal to 50. " +
            "User name must start with letter and consist of A-Z, a-z, 0-9 and _ ")
    private String name;

    @Schema(description = "User surname", defaultValue = "name", type = "string")
    @Size(min = 3, message = "User surname length must be greater than or equals to 3")
    @Size(max = 50, message = "User surname length must be less than or equal to 50")
    @Pattern(regexp = "^[A-Za-z][\\w ]{2,49}$", message = "User surname length must be " +
            "greater than or equal to 4 and must be less than or equal to 50. " +
            "User surname must start with letter and consist of A-Z, a-z, 0-9 and _ ")
    private String surname;

    @Schema(description = "User patronymic", defaultValue = "name", type = "string")
    @Size(min = 3, message = "User patronymic length must be greater than or equals to 3")
    @Size(max = 50, message = "User patronymic length must be less than or equal to 50")
    @Pattern(regexp = "^[A-Za-z][\\w ]{2,49}$", message = "User patronymic length must be " +
            "greater than or equal to 4 and must be less than or equal to 50. " +
            "User patronymic must start with letter and consist of A-Z, a-z, 0-9 and _ ")
    private String patronymic;

    @Schema(description = "User birth", defaultValue = "21.01.1995", type = "string")
    @Pattern(regexp = "^\\d{2}.\\d{2}\\.\\d{4}$",
            message = "User birth pattern dd.MM.yyyy and must be later 1900 " +
                    "and user must be over 18 years old")
    private String birth;

    @Schema(description = "User gender. Recommend values: male, female, not_selected",
            defaultValue = "not_selected", type = "string")
    private String gender;

    @Schema(description = "User address")
    @Valid
    private AddressRequest address;
}
