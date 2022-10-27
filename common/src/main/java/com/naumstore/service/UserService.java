package com.naumstore.service;

import com.naumstore.domain.user.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByLogin(String login);

    void create(User user);

    void update(User user);

    User block(Long id, Boolean isDeleted);
}
