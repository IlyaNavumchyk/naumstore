package com.naumshop.controller;

import com.naumshop.controller.converters.UserMapper;
import com.naumshop.controller.entity_request.UserRequest;
import com.naumshop.controller.request.BlockRequest;
import com.naumshop.domain.user.User;
import com.naumshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static com.naumshop.controller.DefaultResponseTag.USER;
import static com.naumshop.controller.DefaultResponseTag.USERS;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<Object> findAll() {

        List<User> users = userService.findAll();

        return new ResponseEntity<>(
                Collections.singletonMap(USERS, userMapper.mapToResponse(users)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") String id) {

        Long userId = Long.parseLong(id);

        User user = userService.findById(userId);

        return new ResponseEntity<>(
                Collections.singletonMap(USERS, userMapper.mapToResponse(user)),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserRequest userDTO) {

        User user = userMapper.mapToCreate(userDTO);

        userService.create(user);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, userService.findById(user.getId())),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id,
                                         @RequestBody UserRequest userDTO) {

        long userId = Long.parseLong(id);

        User user = userService.findById(userId);

        userMapper.mapToUpdate(userDTO, user);

        userService.update(user);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, userMapper.mapToResponse(user)),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> block(@PathVariable("id") String id,
                                        @RequestBody BlockRequest request) {

        long userId = Long.parseLong(id);
        Boolean isDeleted = request.getIsDeleted();

        User user = userService.block(userId, isDeleted);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, userMapper.mapToResponse(user)),
                HttpStatus.OK
        );
    }
}
