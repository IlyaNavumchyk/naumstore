package com.naumstore.controller.test;

import com.naumstore.domain.role.Role;
import com.naumstore.domain.user.User;
import com.naumstore.repository.RoleRepository;
import com.naumstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public List<Role> test(User user) {

        List<Role> all = roleRepository.findAll();

        return all;
    }
}
