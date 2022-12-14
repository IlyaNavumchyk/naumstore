package com.naumstore.controller.util;

import java.time.LocalDate;

public class UserBirthUtil {

    private UserBirthUtil() {
    }

    public static final LocalDate MIN_BIRTH = LocalDate.of(1900, 1, 1);

    public static boolean isValidBirth(LocalDate userBirth) {

        return (!userBirth.isBefore(MIN_BIRTH)) &&
                (!userBirth.isAfter(LocalDate.now().minusYears(18)));
    }
}
