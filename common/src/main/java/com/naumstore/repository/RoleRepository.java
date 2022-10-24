package com.naumstore.repository;

import com.naumstore.domain.role.Role;
import com.naumstore.domain.role.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(UserRoles roleName);
}
