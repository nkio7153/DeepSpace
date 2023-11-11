package com.depthspace.user.dao;

import com.depthspace.user.dto.UserRegisterRequest;
import com.depthspace.user.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}