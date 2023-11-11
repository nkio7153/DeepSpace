package com.depthspace.admin.dto;

import javax.validation.constraints.NotBlank;

public class AdminAdjustProfileRequest {
    @NotBlank(message = "密碼不可為空")
    private String adminPwd;

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
    
    
}
