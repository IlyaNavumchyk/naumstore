package com.naumstore.controller.controllers_user;

import com.naumstore.controller.converter.UserMapper;
import com.naumstore.controller.entity_request.UserRequest;
import com.naumstore.controller.entity_response.UserResponse;
import com.naumstore.controller.request.BlockRequest;
import com.naumstore.domain.role.UserRoles;
import com.naumstore.domain.user.User;
import com.naumstore.exception.ForbiddenException;
import com.naumstore.security.util.PrincipalUtil;
import com.naumstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.naumstore.controller.DefaultResponseTag.USER;
import static com.naumstore.controller.DefaultResponseTag.USERS;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    @Operation(summary = "User info", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token",
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    public ResponseEntity<Object> findById(@PathVariable("id") String id,
                                           Principal principal) {

        Long userId = Long.parseLong(id);

        User user = userService.findById(userId);

        Object userResponse;

        if (checkAuthority(user, principal)) {
            userResponse = userMapper.mapToResponse(user);
        } else {
            userResponse = userMapper.mapToDefaultResponse(user);
        }

        return new ResponseEntity<>(
                Collections.singletonMap(USERS, userResponse),
                HttpStatus.OK
        );
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Create a new user. Return new user.")
    public ResponseEntity<Object> create(@RequestBody @Valid UserRequest userRequest) {

        User user = userMapper.mapToCreate(userRequest);

        user.getCredentials().setPassword(passwordEncoder.encode(userRequest.getCredentials().getPassword()));

        userService.create(user);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, userMapper.mapToResponse(user)),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    public ResponseEntity<Map<String, UserResponse>> update(@PathVariable("id") String id,
                                                            @RequestBody @Valid UserRequest userRequest,
                                                            Principal principal) {

        long userId = Long.parseLong(id);

        User user = userService.findById(userId);

        userMapper.mapToUpdate(userRequest, user);

        if (checkAuthority(user, principal)) {
            userService.update(user);
        } else {
            throw new ForbiddenException("No authority");
        }

        return new ResponseEntity<>(
                Collections.singletonMap(USER, userMapper.mapToResponse(user)),
                HttpStatus.OK
        );
    }

    private boolean checkAuthority(User user, Principal principal) {

        if (principal != null) {
            return (PrincipalUtil.getUsername(principal).equals(user.getCredentials().getLogin())) ||
                    (PrincipalUtil.isUserHaveAuthority(principal, UserRoles.ROLE_MODERATOR, UserRoles.ROLE_ADMIN));
        }
        return false;
    }
}
