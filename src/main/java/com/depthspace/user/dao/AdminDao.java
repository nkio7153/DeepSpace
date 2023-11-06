package com.depthspace.user.dao;

import com.depthspace.user.dto.AdminRegisterRequest;
import com.depthspace.user.model.Admin;

public interface AdminDao {

    Admin getUserById(Integer userId);

    Admin getUserByEmail(String email);

    Integer createUser(AdminRegisterRequest adminRegisterRequest);
}
