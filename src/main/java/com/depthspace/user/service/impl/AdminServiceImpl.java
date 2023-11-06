package com.depthspace.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import com.depthspace.user.dao.AdminDao;
import com.depthspace.user.dto.AdminLoginRequest;
import com.depthspace.user.dto.AdminRegisterRequest;
import com.depthspace.user.model.Admin;
import com.depthspace.user.service.AdminService;

@Component
public class AdminServiceImpl implements AdminService {

    private  final static Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getUserById(Integer userId) {
        return adminDao.getUserById(userId);
    }

    @Override
    public Integer register(AdminRegisterRequest adminRegisterRequest) {
        // 檢查註冊的 email
       Admin user = adminDao.getUserByEmail(adminRegisterRequest.getEmail());

       if (user != null) {
           log.warn("該 email {} 已經被註冊", adminRegisterRequest.getEmail());
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }

       // 使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(adminRegisterRequest.getPassword().getBytes());
        adminRegisterRequest.setPassword(hashedPassword);

       // 創建帳號
       return adminDao.createUser(adminRegisterRequest);
    }

    @Override
    public Admin login(AdminLoginRequest adminLoginRequest) {
        Admin user = adminDao.getUserByEmail(adminLoginRequest.getEmail());

        // 檢查 user 是否存在
        if (user == null) {
            log.warn("該 email {} 尚未註冊", adminLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(adminLoginRequest.getPassword().getBytes());

        // 比較密碼
        if (user.getPassword().equals(hashedPassword)) {
            return user;
        } else {
            log.warn("email {} 的密碼不正確", adminLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}