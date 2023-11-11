package com.depthspace.admin.dto;

import java.util.List;

public class QueryAdminAuthorities {
    private String adminAcc;
    private List<AdminAuthorities> adminAuthoritiesList;
    
	public String getAdminAcc() {
		return adminAcc;
	}
	public void setAdminAcc(String adminAcc) {
		this.adminAcc = adminAcc;
	}
	public List<AdminAuthorities> getAdminAuthoritiesList() {
		return adminAuthoritiesList;
	}
	public void setAdminAuthoritiesList(List<AdminAuthorities> adminAuthoritiesList) {
		this.adminAuthoritiesList = adminAuthoritiesList;
	}
    
    
}
