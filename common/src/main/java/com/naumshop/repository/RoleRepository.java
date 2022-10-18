package com.naumshop.repository;

import com.naumshop.domain.role.Role;
import com.naumshop.domain.role.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(UserRoles roleName);
}
