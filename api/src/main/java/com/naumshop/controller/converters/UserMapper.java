package com.naumshop.controller.converters;

import com.naumshop.controller.dto.user.UserDTOForCreate;
import com.naumshop.controller.dto.user.UserDTOForUpdate;
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
    User mapForCreate(UserDTOForCreate userDTO);

    @Mapping(target = "birth", expression = "java(parseFromLocalDate(user.getBirth()))")
    UserDTOForCreate mapForCreate(User user);

    @Mapping(target = "birth", expression = "java(parseToLocalDate(userDTO.getBirth()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapForUpdate(UserDTOForUpdate userDTO, @MappingTarget User user);

    @Mapping(target = "birth", expression = "java(parseFromLocalDate(user.getBirth()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapForUpdate(User user, @MappingTarget UserDTOForUpdate userDTO);

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
