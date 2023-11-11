package com.depthspace.admin.controller;

import com.depthspace.admin.dto.AdminLoginRequest;
import com.depthspace.admin.dto.AdminQueryResponse;
import com.depthspace.admin.dto.CreateAdminRequest;
import com.depthspace.admin.dto.QueryAdminAuthorities;
import com.depthspace.admin.model.service.AdminService;
import com.depthspace.utils.commonDto.ResultResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
@Slf4j
@Api(tags = "管理員管理")
@RequestMapping("/manager")
@RestController
@Validated
public class AdminController {
    @Autowired
    private AdminService adminService;
    /**
     * 管理員登入
     * */
    @ApiOperation("管理員登入")
    @PostMapping("/login")
    public ResponseEntity<ResultResponse<String>> managerLogin(@RequestBody @Valid AdminLoginRequest adminLoginRequest) throws JsonProcessingException {
        ResultResponse rs =managerService.adminLogin(adminLoginRequest);
        return  ResponseEntity.status(200).body(rs);
    }
    /**
     * 新增管理員
     * */
    @ApiOperation("新增管理員")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization_M", value = "Admin Access Token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping("/adminAdmin")
    @PreAuthorize("hasAnyAuthority('管理員管理')")
    public ResponseEntity<ResultResponse<String>> createAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest){
        ResultResponse rs =adminService.createAdmin(createAdminRequest);
        return  ResponseEntity.status(201).body(rs);
    }
    /**
     * 查詢管理員
     * */
    @ApiOperation("查詢管理員")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization_M", value = "Admin Access Token", required = true, dataType = "string", paramType = "header")
    })
    @PreAuthorize("hasAnyAuthority('管理員管理')")
    @GetMapping("/adminAdmin")
    public  ResultResponse<ResponsePage<List<AdminQueryResponse>>> getManagers(@RequestParam(required = false) String search,
                                                                         @RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                                         @RequestParam(defaultValue = "5") @Min(1) Integer size){
        ResultResponse rs =new ResultResponse();
        QueryAdminParameter queryAdminParameter =new QueryAdminParameter();
        queryAdminParameter.setSearch(search);
        queryAdminParameter.setSize(size);
        queryAdminParameter.setPage(page);
        ResponsePage<List<AdminQueryResponse>> pgList =adminService.getManagers(queryAdminParameter);
        rs.setMessage(pgList);
        return  rs;
    }

    /**
     * 修改管理員資料
     * */
    @ApiOperation("修改管理員資料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization_M", value = "Admin Access Token", required = true, dataType = "string", paramType = "header")
    })
    @PreAuthorize("hasAnyAuthority('管理員管理')")
    @PutMapping("/adminAdmin")
    public  ResponseEntity<ResultResponse<String>> adjustManager(@RequestBody @Valid AdjustAdminRequest adjustAdminRequest){
        ResultResponse rs =adminService.adjustManager(adjustAdminRequest);
        return  ResponseEntity.status(200).body(rs);
    }



    /**
     * 查詢自身管理員權限
     * */
    @ApiOperation("查詢自身管理員權限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization_M", value = "Admin Access Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/authorities")
    public  ResponseEntity<ResultResponse<QueryAdminAuthorities>> getAuthorities(@ApiParam(hidden = true)@RequestAttribute Integer adminId){
        ResultResponse rs =adminService.getAdminAuthoritiesById(adminId);
        return  ResponseEntity.status(200).body(rs);
    }

    /**
     * 查詢管理員權限(來自form表單 ，必須先查詢該管理員 ，再把管理員名稱透過參數呼叫此API)
     * */
    @ApiOperation("查詢管理員權限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization_M", value = "Admin Access Token", required = true, dataType = "string", paramType = "header")
    })
    @PreAuthorize("hasAnyAuthority('管理員管理')")
    @GetMapping("/manageManager/authorities")
    public  ResponseEntity<ResultResponse<QueryAdminAuthorities>> getManagerAuthoritiesByAccount(@RequestParam @NotBlank String adminAccount){
        ResultResponse rs =adminService.getAdminAuthoritiesByAccount(adminAccount);
        return  ResponseEntity.status(200).body(rs);
    }
    /**
     * 修改管理員權限
     * */
    @PreAuthorize("hasAnyAuthority('管理員管理')")
    @ApiOperation("修改管理員權限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization_M", value = "Admin Access Token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping("/manageManager/authorities")
    public  ResponseEntity<ResultResponse<String>> adjustPermission(@RequestBody @Valid AdjustPermissionRequest adjustPermissionRequest){
        ResultResponse rs =adminService.adjustPermission(adjustPermissionRequest);
        return  ResponseEntity.status(200).body(rs);
    }

    @ApiOperation("查詢個人資訊")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization_M", value = "Admin Access Token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/profile")
    public ResponseEntity<ResultResponse< AdminProfileResponse>> adminProfile( @ApiParam(hidden = true) @RequestAttribute("adminId")Integer adminId){
    	AdminProfileResponse managerProfileResponse =adminService.getProfile(adminId);
        ResultResponse rs =new ResultResponse();
        rs.setMessage(managerProfileResponse);
        return  ResponseEntity.status(200).body(rs);
    }
    @ApiOperation("修改自身密碼")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization_M", value = "Admin Access Token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping("/profile")
    public  ResponseEntity<ResultResponse<String>> adjustProfile(@ApiParam(hidden = true) @RequestAttribute("adminId")Integer adminId,
                                            @RequestBody @Valid AdminAdjustProfileRequest adminAdjustProfileRequest){

        adminService.adjustProfile(adminId,adminAdjustProfileRequest);
        ResultResponse rs =new ResultResponse();
        rs.setMessage("修改成功");
        return  ResponseEntity.status(200).body(rs);
    }

}
