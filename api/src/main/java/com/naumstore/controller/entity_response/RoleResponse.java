package com.naumstore.controller.entity_response;

import com.naumstore.domain.role.UserRoles;
import lombok.Data;

@Data
public class RoleResponse {

    private Integer id;

    private UserRoles roleName;
}
