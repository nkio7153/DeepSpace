package com.depthspace.admin.dto;

public class AdminQueryResponse {
    private String adminAcc;
    private String adminCreated;
    private String adminStatus; //0關閉 ，1 啟用
    
	public String getAdminAcc() {
		return adminAcc;
	}
	public void setAdminAcc(String adminAcc) {
		this.adminAcc = adminAcc;
	}
	public String getAdminCreated() {
		return adminCreated;
	}
	public void setAdminCreated(String adminCreated) {
		this.adminCreated = adminCreated;
	}
	public String getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}
    
    
}
