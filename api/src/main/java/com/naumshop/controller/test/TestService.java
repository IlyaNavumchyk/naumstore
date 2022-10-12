package com.naumshop.controller.test;

import com.naumshop.domain.role.Role;
import com.naumshop.domain.user.User;
import com.naumshop.repository.RoleRepository;
import com.naumshop.repository.UserRepository;
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
