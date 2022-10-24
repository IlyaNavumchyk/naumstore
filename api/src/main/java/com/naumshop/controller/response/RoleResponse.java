package com.naumshop.controller.response;

import com.naumshop.domain.role.UserRoles;
import lombok.Data;

@Data
public class RoleResponse {

    private Integer id;

    private UserRoles roleName;
}
