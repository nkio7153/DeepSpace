package com.depthspace.admin.service;

import com.depthspace.admin.dto.AdjustAdminRequest;
import com.depthspace.admin.dto.AdjustPermissionRequest;
import com.depthspace.admin.dto.AdminAdjustProfileRequest;
import com.depthspace.admin.dto.CreateAdminRequest;
import com.depthspace.admin.dto.QueryAdminParameter;
import com.depthspace.admin.dto.AdminLoginRequest;
import com.depthspace.admin.dto.AdminProfileResponse;
import com.depthspace.admin.dto.AdminQueryResponse;
import com.depthspace.utils.commonDto.ResponsePage;
import com.depthspace.utils.commonDto.ResultResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public interface AdminService {
    ResultResponse createManager(CreateAdminRequest createAdminRequest);
    ResultResponse managerLogin(AdminLoginRequest adminLoginRequest) throws JsonProcessingException;
    ResultResponse adjustPermission(AdjustPermissionRequest adjustPermissionRequest);
    ResultResponse getAdminAuthoritiesById(Integer adminId);
    ResultResponse getAdminAuthoritiesByAccount(String account);
    ResultResponse adjustAdmin(AdjustAdminRequest adjustAdminRequest);

    ResponsePage<List<AdminQueryResponse>> getManagers(QueryAdminParameter queryAdminParameter);

    AdminProfileResponse getProfile(Integer managerId);

    void adjustProfile(Integer managerId,AdminAdjustProfileRequest adminAdjustProfileRequest);

}
