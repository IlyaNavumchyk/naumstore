package com.naumshop.dto;

import com.naumshop.domain.user.User;
import com.naumshop.dto.user.UserDTOForCreate;
import com.naumshop.dto.user.UserDTOForUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
public interface UserMapper {

    @Mappings(value = {
            @Mapping(target = "birth", expression = "java(parseToLocalDate(userDTO.getBirth()))")
    })
    User mapForCreate(UserDTOForCreate userDTO);

    @Mappings(value = {
            @Mapping(target = "birth", expression = "java(parseFromLocalDate(user.getBirth()))")
    })
    UserDTOForCreate mapForCreate(User user);

    User mapForUpdate(UserDTOForUpdate userDTO);

    UserDTOForUpdate mapForUpdate(User user);

    default LocalDate parseToLocalDate(String birth) {

        if (birth == null) {
            return null;
        }

        return LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    default String parseFromLocalDate(LocalDate birth) {

        if (birth == null) {
            return null;
        }

        // yyyy-MM-dd
        String[] args = birth.toString().split("-");

        // dd.MM.yyyy
        return args[2] + '.' + args[1] + '.' + args[0];
    }
}
