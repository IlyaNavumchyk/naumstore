package com.naumstore.controller.util;

import com.naumstore.domain.user.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserMapperUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDate parseToLocalDate(String birth) {

        if (birth == null) {
            return null;
        }

        return LocalDate.parse(birth, DATE_TIME_FORMATTER);
    }

    public static String parseFromLocalDate(LocalDate birth) {

        if (birth == null) {
            return null;
        }

        // yyyy-MM-dd
        String[] args = birth.toString().split("-");

        // dd.MM.yyyy
        return args[2] + '.' + args[1] + '.' + args[0];
    }

    public static Gender getGender(String gender) {

        if (gender == null) {
            return null;
        }

        return Gender.valueOf(gender.toUpperCase());
    }
}
