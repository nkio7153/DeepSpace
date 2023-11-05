package com.depthspace.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.depthspace.user.dto.UserLoginRequest;
import com.depthspace.user.dto.UserRegisterRequest;
import com.depthspace.user.model.User;
import com.depthspace.user.service.UserService;

import javax.validation.Valid;

@Validated
@CrossOrigin(origins = "http://localhost:8080") // 假設你的前端跑在 localhost:8080
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
       User user = userService.login(userLoginRequest);

       return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
