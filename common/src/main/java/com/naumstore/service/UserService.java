package com.naumstore.service;

import com.naumstore.domain.role.Role;
import com.naumstore.domain.role.UserRoles;
import com.naumstore.domain.user.User;
import com.naumstore.exception.NoSuchEntityException;
import com.naumstore.repository.RoleRepository;
import com.naumstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAllByOrderById();
    }

    public User findById(Long id) {

        return userRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("User with this id \"%s\" not found", id)));
    }

    @Transactional
    public void create(User user) {

        Role roleUser = roleRepository.findByRoleName(UserRoles.ROLE_USER);

        user.setRoles(Set.of(roleUser));
        roleUser.getUsers().add(user);

        userRepository.save(user);
    }

    @Transactional
    public void update(User user) {

        userRepository.save(user);
    }

    @Transactional
    public User block(Long id, Boolean isDeleted) {

        User user = findById(id);

        user.setIsDeleted(isDeleted);
        update(user);

        return user;
    }
}