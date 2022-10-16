package com.naumshop.controller.test;

import com.naumshop.repository.UserRepository;
import com.naumshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ProductService productService;
    private final UserRepository userRepository;

    @GetMapping("/test")
    public ResponseEntity<Object> test(@RequestParam String test) {

        return new ResponseEntity<>(
                Collections.singletonMap("test", null),
                HttpStatus.OK
        );
    }
}
