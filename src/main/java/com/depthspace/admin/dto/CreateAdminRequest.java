package com.depthspace.admin.dto;

import javax.validation.constraints.NotBlank;

public class CreateAdminRequest {
    @NotBlank(message = "adminAcc不可為空")
    private  String adminAcc;
    @NotBlank(message = "adminPwd不可為空")
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
