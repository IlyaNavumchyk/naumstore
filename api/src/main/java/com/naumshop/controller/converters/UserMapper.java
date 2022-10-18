package com.naumshop.controller.converters;

import com.naumshop.controller.dto.user.UserDTO;
import com.naumshop.domain.user.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
public interface UserMapper {
    @Mapping(target = "birth", expression = "java(parseToLocalDate(userDTO.getBirth()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User mapForCreate(UserDTO userDTO);

    @Mapping(target = "birth", expression = "java(parseToLocalDate(userDTO.getBirth()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapForUpdate(UserDTO userDTO, @MappingTarget User user);

    default LocalDate parseToLocalDate(String birth) {

        return LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    default String parseFromLocalDate(LocalDate birth) {

        // yyyy-MM-dd
        String[] args = birth.toString().split("-");

        // dd.MM.yyyy
        return args[2] + '.' + args[1] + '.' + args[0];
    }
}
