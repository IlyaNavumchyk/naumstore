package com.naumstore.controller.converter;

import com.naumstore.controller.entity_request.UserRequest;
import com.naumstore.controller.entity_response.UserDefaultResponse;
import com.naumstore.controller.entity_response.UserResponse;
import com.naumstore.domain.user.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(uses = OrderMapper.class)
public interface UserMapper {

    @Mapping(target = "birth", expression =
            "java(com.naumstore.controller.util.UserMapperUtil.parseToLocalDate(userRequest.getBirth()))")
    @Mapping(target = "gender", expression =
            "java(com.naumstore.controller.util.UserMapperUtil.getGender(userRequest.getGender()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User mapToCreate(UserRequest userRequest);

    @Mapping(target = "birth", expression =
            "java(com.naumstore.controller.util.UserMapperUtil.parseToLocalDate(userRequest.getBirth()))")
    @Mapping(target = "gender", expression =
            "java(com.naumstore.controller.util.UserMapperUtil.getGender(userRequest.getGender()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapToUpdate(UserRequest userRequest, @MappingTarget User user);

    @Mapping(target = "birth", expression =
            "java(com.naumstore.controller.util.UserMapperUtil.parseFromLocalDate(user.getBirth()))")
    UserResponse mapToResponse(User user);

    @Mapping(target = "birth", expression =
            "java(com.naumstore.controller.util.UserMapperUtil.parseFromLocalDate(user.getBirth()))")
    UserDefaultResponse mapToDefaultResponse(User user);

    List<UserResponse> mapToResponse(List<User> user);
}
