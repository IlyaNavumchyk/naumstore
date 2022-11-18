package com.naumstore.controller.controllers_admin;

import com.naumstore.controller.converter.UserMapper;
import com.naumstore.controller.request.BlockRequest;
import com.naumstore.domain.user.User;
import com.naumstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static com.naumstore.controller.DefaultResponseTag.USER;
import static com.naumstore.controller.DefaultResponseTag.USERS;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class AdminUserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    @Operation(summary = "Find all users", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    public ResponseEntity<Object> findAll() {

        List<User> users = userService.findAll();

        return new ResponseEntity<>(
                Collections.singletonMap(USERS, userMapper.mapToResponse(users)),
                HttpStatus.OK
        );
    }

    /**
     * This method need to blocks or software remove the user.
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Block user", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
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
