package com.depthspace.admin.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "ADMIN")
public class AdminVO implements Serializable{
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AI鍵要加
	@Column(name = "ADMIN_ID") 
	private Integer adminId;
	@Expose
	@Column(name = "ADMIN_NAME")
	private String adminName;
	@Expose
	@Column(name = "ADMIN_ACC")
	private String adminAcc;
	@Expose
	@Column(name = "ADMIN_PWD")
	private String adminPwd;
	@Expose
	@Column(name = "ADMIN_STATUS")
	private Byte adminStatus;
	@Expose
	@Column(name = "ADMIN_VERIFY_STATUS", columnDefinition = "TINYINT")
	private Byte adminVerifyStatus;
	@Expose
	@Column(name = "ADMIN_FUNC_NAME", columnDefinition = "TINYINT")
	private Byte adminFuncName;
	
	public AdminVO() {
    }

	public AdminVO(Integer adminId, String adminName, String adminAcc, String adminPwd, Byte adminStatus,
			Byte adminVerifyStatus, Byte adminFuncName) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminAcc = adminAcc;
		this.adminPwd = adminPwd;
		this.adminStatus = adminStatus;
		this.adminVerifyStatus = adminVerifyStatus;
		this.adminFuncName = adminFuncName;
	}

	public AdminVO(String adminName, String adminAcc, String adminPwd, Byte adminStatus, Byte adminVerifyStatus,
			Byte adminFuncName) {
		super();
		this.adminName = adminName;
		this.adminAcc = adminAcc;
		this.adminPwd = adminPwd;
		this.adminStatus = adminStatus;
		this.adminVerifyStatus = adminVerifyStatus;
		this.adminFuncName = adminFuncName;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
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

	public Byte getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(Byte adminStatus) {
		this.adminStatus = adminStatus;
	}

	public Byte getAdminVerifyStatus() {
		return adminVerifyStatus;
	}

	public void setAdminVerifyStatus(Byte adminVerifyStatus) {
		this.adminVerifyStatus = adminVerifyStatus;
	}

	public Byte getAdminFuncName() {
		return adminFuncName;
	}

	public void setAdminFuncName(Byte adminFuncName) {
		this.adminFuncName = adminFuncName;
	}
	
}