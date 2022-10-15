package com.naumshop.service;

import com.naumshop.domain.role.Role;
import com.naumshop.domain.role.UserRoles;
import com.naumshop.domain.user.User;
import com.naumshop.repository.RoleRepository;
import com.naumshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {

        return userRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Transactional
    public void create(User user) {

        Role roleUser = roleRepository.findByRoleName(UserRoles.ROLE_USER);
        Role roleAnon = roleRepository.findByRoleName(UserRoles.ROLE_ANONYMOUS);

        user.setRoles(Set.of(roleUser, roleAnon));
        roleUser.getUsers().add(user);
        roleAnon.getUsers().add(user);

        userRepository.save(user);
    }

    @Transactional
    public void update(User user) {

        userRepository.save(user);
    }
}
