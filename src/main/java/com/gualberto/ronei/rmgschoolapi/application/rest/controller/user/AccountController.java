package com.gualberto.ronei.rmgschoolapi.application.rest.controller.user;


import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import com.gualberto.ronei.rmgschoolapi.domain.user.UserForm;
import com.gualberto.ronei.rmgschoolapi.domain.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {

    private final UserService userService;


    @Transactional
    @PostMapping()
    public ResponseEntity<UserResponse> createAccount(@RequestBody UserRequest request) {

        UserForm form = request.toUserForm();

        User savedUser = userService.createUser(form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        UserResponse response = UserResponse.fromUser(savedUser);

        return ResponseEntity.created(location).body(response);
    }
}