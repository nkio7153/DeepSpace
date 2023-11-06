package com.depthspace.user.service;

import com.depthspace.user.dto.AdminLoginRequest;
import com.depthspace.user.dto.AdminRegisterRequest;
import com.depthspace.user.model.Admin;

public interface AdminService {

    Admin getUserById(Integer userId);

    Integer register(AdminRegisterRequest adminRegisterRequest);

    Admin login(AdminLoginRequest adminLoginRequest);
}