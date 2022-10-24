package com.naumshop.controller.converters;

import com.naumshop.controller.entity_request.UserRequest;
import com.naumshop.controller.response.UserResponse;
import com.naumshop.domain.user.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(uses = OrderMapper.class)
public interface UserMapper {

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Mapping(target = "birth", expression = "java(parseToLocalDate(userDTO.getBirth()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User mapToCreate(UserRequest userDTO);

    @Mapping(target = "birth", expression = "java(parseToLocalDate(userDTO.getBirth()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapToUpdate(UserRequest userDTO, @MappingTarget User user);

    @Mapping(target = "birth", expression = "java(parseFromLocalDate(user.getBirth()))")
    UserResponse mapToResponse(User user);

    List<UserResponse> mapToResponse(List<User> user);

    default LocalDate parseToLocalDate(String birth) {

        return LocalDate.parse(birth, DATE_TIME_FORMATTER);
    }

    default String parseFromLocalDate(LocalDate birth) {

        // yyyy-MM-dd
        String[] args = birth.toString().split("-");

        // dd.MM.yyyy
        return args[2] + '.' + args[1] + '.' + args[0];
    }
}
