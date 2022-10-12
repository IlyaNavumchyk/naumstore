package com.naumshop.controller.test;

import com.naumshop.domain.user.User;
import com.naumshop.dto.UserMapper;
import com.naumshop.dto.user.UserDTOForCreate;
import com.naumshop.dto.user.UserDTOForUpdate;
import com.naumshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserMapper userMapper;
    private final TestService testService;

    @GetMapping("/test")
    public ResponseEntity<Object> test(@RequestBody UserDTOForCreate userDTO) {



        return new ResponseEntity<>(
                Collections.singletonMap("test", null),
                HttpStatus.OK
        );
    }
}
