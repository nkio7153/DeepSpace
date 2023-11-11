package com.depthspace.admin.vo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "ADMIN")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_ID")
    private Integer adminId;
    @Column(name = "ADMIN_ACC")
    private String  adminAcc;
    @Column(name = "ADMIN_PWD")
    private String  adminPwd;
    @Column(name = "ADMIN_CREATED" , columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private java.util.Date adminCreated;
    @Column(name = "ADMIN_STATUS" , columnDefinition = "TINYINT NOT NULL DEFAULT 1")
    private Integer adminStatus;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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
	public java.util.Date getAdminCreated() {
		return adminCreated;
	}
	public void setAdminCreated(java.util.Date adminCreated) {
		this.adminCreated = adminCreated;
	}
	public Integer getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}
    
    
}

