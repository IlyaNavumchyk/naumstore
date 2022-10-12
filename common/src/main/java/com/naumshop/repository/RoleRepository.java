package com.naumshop.repository;

import com.naumshop.domain.role.Role;
import com.naumshop.domain.role.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "insert into shop.l_user_role (user_id, role_id, creation_date) values " +
            "(:user_id, 1, current_timestamp(6))," +
            "(:user_id, 4, current_timestamp(6))", nativeQuery = true)
    @Modifying(flushAutomatically = true)
    void addInitialsRoles(@Param("user_id") Long userId);

    Role findByRoleName(UserRoles roleName);

//    @Query(value = "select r from Role r where r.roleName in :roleNames")
//    Set<Role> getInitialsRoles(@Param(":roleNames") Collection<String> range);
}
