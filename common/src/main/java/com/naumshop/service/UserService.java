package com.naumshop.service;

import com.naumshop.domain.user.Address;
import com.naumshop.domain.user.User;
import com.naumshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User test() {

        Optional<User> byId = userRepository.findById(1L);

        User user = byId.orElseThrow(NullPointerException::new);

        Address address = new Address();
        address.setCountry("Belarus");
        address.setCity("Zelva");

        user.setAddress(address);

        userRepository.save(user);

        return user;
    }

    @Transactional
    public void create(User user) {

        userRepository.save(user);
    }
}
