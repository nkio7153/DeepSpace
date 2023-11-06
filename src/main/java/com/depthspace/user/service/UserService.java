package com.depthspace.user.service;

import com.depthspace.user.dto.UserLoginRequest;
import com.depthspace.user.dto.UserRegisterRequest;
import com.depthspace.user.model.User;

public interface UserService {
	
    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}