package com.depthspace.admin.service.imp;

import com.depthspace.admin.vo.Admin;
import com.depthspace.admin.dao.AdminRepository;
import com.depthspace.admin.dao.PermissionRepository;
import com.depthspace.admin.dto.AdjustAdminRequest;
import com.depthspace.admin.dto.AdjustPermissionRequest;
import com.depthspace.admin.dto.AdminAdjustProfileRequest;
import com.depthspace.admin.dto.AdminLoginRequest;
import com.depthspace.admin.dto.AdminProfileResponse;
import com.depthspace.admin.dto.AdminQueryResponse;
import com.depthspace.admin.dto.CreateAdminRequest;
import com.depthspace.admin.dto.QueryAdminParameter;
import com.depthspace.admin.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationAdmin;


import com.depthspace.utils.AdminJwtUtil;
import com.depthspace.utils.commonDto.ResultResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImp  implements AdminService {
    @Autowired
    private AuthenticationAdmin authenticationAdmin;
    @Autowired
    private  AdminJwtUtil AdminJwtUtil;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private  ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    /**
     * 新增管理員
     * */
    public ResultResponse createadmin(CreateAdminRequest createAdminRequest){
        Admin admin=adminRepository.findByAdminAcc(createAdminRequest.getAdminAcc());
        if(admin!=null)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"已有重複管理員");
        admin =new Admin();
        admin.setAdminAcc(createAdminRequest.getAdminAcc());
        String encodePwd=bcryptEncoder.encode(createAdminRequest.getAdminPwd());
        admin.setAdminPwd(encodePwd);
        adminRepository.save(admin);
        ResultResponse loginResponse=new ResultResponse();
        loginResponse.setMessage("新增成功");
        return loginResponse;
    }
    /**
     * 管理員登入
     * */
    public ResultResponse adminLogin(AdminLoginRequest adminLoginRequest) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(adminLoginRequest.getAdminAcc(),adminLoginRequest.getAdminPwd());
        //authenticationadmin.authenticate 調用  UserDetailsService進行驗證
        Authentication authentication= authenticationAdmin.authenticate(authenticationToken);
        if(Objects.isNull(authentication)) //返回空值代表認證失敗
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"認證錯誤");
        AdminDetailsImp adminDetail = (AdminDetailsImp) authentication.getPrincipal();
        if(adminDetail.getAdmin().getAdminStatus()==0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"您已被停權");
        String adminId =String.valueOf( adminDetail.getAdmin().getAdminId());
        String adminDetailJson=null;
        adminDetailJson=objectMapper.writeValueAsString(adminDetail);
        redisTemplate.opsForValue().set("admin:Login:"+adminDetail.getadmin().getadminId(),adminDetailJson);
        String jwt= AdminJwtUtil.createJwt(adminId);
        ResultResponse responseResult=new ResultResponse();
        responseResult.setMessage(jwt);
        return  responseResult;
    }
    /**
     * 修改管理員權限
     * */
    @Transactional
    public  ResultResponse adjustPermission(AdjustPermissionRequest adjustPermissionRequest){
        Admin admin=adminRepository.findByAdminAcc(adjustPermissionRequest.getAccount());
        if(admin==null)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此管理員");
        Integer adminId=admin.getAdminId();
        List<String> authorities=  adminRepository.findAdminFuncsById(adminId);
        if(authorities.contains( AdminAuthorities.管理員管理.name()))
               throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"最高管理員不可更改自身權限");
        //先刪除全部權限
        adminRepository.deleteAllAuthoritiesById(adminId);
        List<String> stringList = new ArrayList<>();
        for (AdminAuthorities enumValue : adjustPermissionRequest.getAuthorities()) {
            stringList.add(enumValue.name());
        }
        //更新權限
        permissionRepository.batchUpdatePermission(adminId,stringList);
        redisTemplate.delete("Admin:Login:"+adminId);//需重新登入
        ResultResponse rs =new ResultResponse();
        rs.setMessage("更新完成");
        return  rs;
    }
    /**
     * 獲取管理員權限
     * */
    public  ResultResponse getadminAuthoritiesById(Integer adminId){
        ResultResponse rs =new ResultResponse();
        Admin admin =adminRepository.findById(adminId).orElse(null);
        List<AdminAuthorities> adminAuthoritiesList =new ArrayList<>();
        List<String> adminFuncs=adminRepository.findAdminFuncsById(adminId);
        adminFuncs.forEach(function ->{
            AdminAuthorities adminAuthorities=AdminAuthorities.valueOf(function);
            adminAuthoritiesList.add(adminAuthorities);
        } );
        QueryadminAuthorities queryadminAuthorities=new QueryadminAuthorities();
        queryadminAuthorities.setAdminAccount(admin.getAdminAccount());
        queryadminAuthorities.setAdminAuthoritiesList(adminAuthoritiesList);
        rs.setMessage(queryadminAuthorities);
        return  rs;
    }
    /**
     * 查詢管理員權限
     * */
    public  ResultResponse getAdminAuthoritiesByAccount(String account){
        ResultResponse rs =new ResultResponse();
        List<AdminAuthorities> adminAuthoritiesList =new ArrayList<>();
        List<String> adminFunctions=adminRepository.findAdminFuncsByAcc(account);
        adminFunctions.forEach(function ->{
            AdminAuthorities adminAuthorities=AdminAuthorities.valueOf(function);
            adminAuthoritiesList.add(adminAuthorities);
        } );

        QueryadminAuthorities queryadminAuthorities=new QueryAdminAuthorities();
        queryadminAuthorities.setAdminAccount(account);
        queryadminAuthorities.setAdminAuthoritiesList(adminAuthoritiesList);
        rs.setMessage(queryadminAuthorities);
        return  rs;
    }
    /**
     * 最高管理員修改管理員資訊
     * */
    @Transactional
    public ResultResponse adjustadmin(AdjustAdminRequest adjustadminRequest) {
        Aadmin admin =adminRepository.findByAdminAcc(adjustadminRequest.getOrgAdminAcc());
        Aadmin checkSameadmin=adminRepository.findByAdminAcc(adjustadminRequest.getAdminAcc());
        if(admin==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此管理員");
        if(checkSameadmin!=null && admin.getadminId()!=checkSameadmin.getAadminId())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"您取的名字已有人使用");
        admin.setAadminAccount(adjustAadminRequest.getAadminAcc());
        if(adjustAdminRequest.getAadminPwd()!=null && !adjustAdminRequest.getAdminPwd().trim().equals(""))
            admin.setadminPassword(bcryptEncoder.encode(adjustAdminRequest.getAdminPwd()));
        admin.setAdminStatus(adjustAdminRequest.getAdminStatus());
        adminRepository.save(admin);
        ResultResponse rs =new ResultResponse();
        rs.setMessage("修改完成");
        return  rs;
    }
    /**
     * 查詢管理員
     * */
    @Override
    public ResponsePage<List<AdminQueryResponse>> getAdmins(QueryAdminParameter queryAdminParameter) {
        //使用pageable 查詢
        Pageable pageable = PageRequest.of(queryAdminParameter.getPage()-1, queryAdminParameter.getSize(), Sort.by("adminId").ascending());
        Page<Admin> adminPage =adminRepository.findByAadminAcc(queryAdminParameter.getSearch(), pageable);
        List<Admin> adminList = adminPage.getContent();
        List<AdminQueryResponse> adminQueryResponseList=new ArrayList<>();
        for(int i =0 ;i<adminList.size();i++){
            Admin admin =adminList.get(i);
            AdminQueryResponse adminQueryResponse =new AdminQueryResponse();
            adminQueryResponse.setAdminAcc(admin.getAdminAcc());
            adminQueryResponse.setAdminCreated(AllDogCatUtils.timestampToDateFormat(admin.getAdminCreated()));
            adminQueryResponse.setAdminStatus(admin.getAdminStatus()==1?"開啟":"停權");
            adminQueryResponseList.add(adminQueryResponse);
        }
        ResponsePage<List<AdminQueryResponse>> rs =new ResponsePage<>();
        rs.setPage(adminPage.getPageable().getPageNumber()+1);//pageable預設從第0頁開始
        rs.setSize(pageable.getPageSize());
        rs.setTotal((int)adminPage.getTotalElements());
        rs.setBody(adminQueryResponseList);
        return rs;
    }

    /**
     * 管理員查詢自身訊息
     * */
    @Override
    public AdminProfileResponse getProfile(Integer adminId) {

        Admin admin=adminRepository.findById(adminId).orElse(null);
        if(admin==null)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此管理員");
        AdminProfileResponse adminProfileResponse =new AdminProfileResponse();
        adminProfileResponse.setAdminAcc(admin.getAdminAcc());
        adminProfileResponse.setAdminCreated(AllDogCatUtils.timestampToDateFormat(admin.getAdminCreated()));
        String status="";
        switch (admin.getAdminStatus()){
            case 0:
                status="停權";
                break;
            case 1:
                status="開啟";
                break;
        }
        adminProfileResponse.setAdminStatus(status);
        return adminProfileResponse;
    }
    /**
     * 修改自身密碼
     * */
    @Override
    public void adjustProfile(Integer adminId, AdminAdjustProfileRequest adminAdjustProfileRequest) {

        Admin adjustadmin =adminRepository.findById(adminId).orElse(null);
        if(adjustadmin==null)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此使用者");
        String pwd =bcryptEncoder.encode(adminAdjustProfileRequest.getAdminPwd());
        adjustAdmin.setAdminPwd(pwd);
        adminRepository.save(adjustAdmin);
    }
}
