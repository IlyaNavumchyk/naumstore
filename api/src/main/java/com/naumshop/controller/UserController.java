package com.naumshop.controller;

import com.naumshop.domain.user.User;
import com.naumshop.dto.user.UserDTOForCreate;
import com.naumshop.dto.UserMapper;
import com.naumshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private static final String USERS = "users";
    private static final String USER = "user";

    private final UserService userService;

    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<Object> findAll() {

        return new ResponseEntity<>(
                Collections.singletonMap(USERS, userService.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping("/test")
    public ResponseEntity<Object> test() {

        return new ResponseEntity<>(
                Collections.singletonMap("test", userService.test()),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserDTOForCreate userDTO) {

        User user = mapper.mapForCreate(userDTO);

        userService.create(user);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, mapper.mapForCreate(user)),
                HttpStatus.OK
        );
    }
}
