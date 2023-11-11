package com.depthspace.admin.dto;

import javax.validation.constraints.NotBlank;

public class AdminLoginRequest {
    @NotBlank(message = "帳號不可為空")
    private  String adminAcc;
    @NotBlank(message = "密碼不可為空")
    private  String adminPwd;
    
	public String getAdminAcc() {
		return adminAcc;
	}
	public void setAdminAcc(String adminAcc) {
		this.adminAcc = adminAcc;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
    
    
}
