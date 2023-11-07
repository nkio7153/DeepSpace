package com.depthspace.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.depthspace.user.dto.AdminLoginRequest;
import com.depthspace.user.dto.AdminRegisterRequest;
import com.depthspace.user.model.Admin;
import com.depthspace.user.service.AdminService;

import javax.validation.Valid;

@Validated
@CrossOrigin(origins = "http://localhost:8080") // 假設你的前端跑在 localhost:8080
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/users/register")
    public ResponseEntity<Admin> register(@RequestBody @Valid AdminRegisterRequest adminRegisterRequest) {
        Integer userId = adminService.register(adminRegisterRequest);

        Admin user = adminService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<Admin> login(@RequestBody @Valid AdminLoginRequest adminLoginRequest) {
       Admin user = adminService.login(adminLoginRequest);

       return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
