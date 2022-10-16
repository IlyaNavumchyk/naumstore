package com.naumshop.controller;

import com.naumshop.controller.converters.UserMapper;
import com.naumshop.controller.dto.user.UserDTOForCreate;
import com.naumshop.controller.dto.user.UserDTOForUpdate;
import com.naumshop.domain.user.User;
import com.naumshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static com.naumshop.controller.DefaultResponseTag.USER;
import static com.naumshop.controller.DefaultResponseTag.USERS;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<Object> findAll() {

        return new ResponseEntity<>(
                Collections.singletonMap(USERS, userService.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") String id) {

        Long userId = Long.parseLong(id);

        return new ResponseEntity<>(
                Collections.singletonMap(USERS, userService.findById(userId)),
                HttpStatus.OK
        );
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserDTOForCreate userDTO) {

        User user = mapper.mapForCreate(userDTO);

        userService.create(user);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, userService.findById(user.getId())),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody UserDTOForUpdate userDTO) {

        User user = userService.findById(userDTO.getId());

        mapper.mapForUpdate(userDTO, user);

        userService.update(user);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, user),
                HttpStatus.OK
        );
    }
}
