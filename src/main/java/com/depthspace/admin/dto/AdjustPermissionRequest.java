package com.depthspace.admin.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AdjustPermissionRequest {
    @NotBlank(message = "帳號不可為空")
    private String account;
    private List<AdminAuthorities> authorities;
    
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public List<AdminAuthorities> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<AdminAuthorities> authorities) {
		this.authorities = authorities;
	}
    
    
}
