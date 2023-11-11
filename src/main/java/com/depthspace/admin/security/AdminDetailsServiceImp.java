package com.depthspace.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.depthspace.admin.dao.AdminRepository;
import com.depthspace.admin.vo.Admin;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminDetailsServiceImp implements UserDetailsService { 
    @Autowired
    private  AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        //查詢管理員
        Admin admin =adminRepository.findByAdminAcc(username);

        if (admin == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此管理員");
        }
        List<String> functions=adminRepository.findAdminFuncsById(admin.getAdminId());
        //查權限
        List<String> permissionsList=new ArrayList();
        if(functions.size()>0){
            for (String function: functions) {
                permissionsList.add(function);
            }
        }
        //回傳一個 userDetails
        return new AdminDetailsImp(admin,permissionsList);
    }
}
