package com.naumshop.dto.user;

import com.naumshop.domain.user.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserDTOForCreate {

    private CredentialsDTO credentials;

    private String name;

    private String surname;

    private String patronymic;

    private String birth;

    private Gender gender;

    private AddressDTO address;

    private Boolean isDeleted;

    private LocalDateTime creationDate;
}
