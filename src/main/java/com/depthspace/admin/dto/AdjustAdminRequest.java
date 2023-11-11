package com.depthspace.admin.dto;

//import org.springframework.data.relational.core.sql.In;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AdjustAdminRequest {
    //private Integer  adminId;
    @NotBlank(message = "帳號不可為空")
    private  String  orgAdminAcc;//修改前的帳號名稱
    @NotBlank(message = "帳號不可為空")
    private  String  adminAcc;
//    @NotBlank(message = "密碼不可為空")
    private  String  adminPwd;
    @Min(0)
    @Max(1)
    private  Integer adminStatus;
	public String getOrgAdminAcc() {
		return orgAdminAcc;
	}
	public void setOrgAdminAcc(String orgAdminAcc) {
		this.orgAdminAcc = orgAdminAcc;
	}
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
	public Integer getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}
    
    
}
