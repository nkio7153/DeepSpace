package com.depthspace.admin.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")

public class AdminVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ADMIN_ID")
	private Integer adminId;
	
	@Column(name="ADMIN_NAME")
	private String adminName;
	
	@Column(name="ADMIN_ACC")
	private String adminAcc;
	
	@Column(name="ADMIN_PWD")
	private String adminPwd;
	
	@Column(name="ADMIN_STATUS")
	private Integer adminStatus;
	
	public AdminVO() {
		super();
	}

	public AdminVO(Integer adminId, String adminName, String adminAcc, String adminPwd, Integer adminStatus) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminAcc = adminAcc;
		this.adminPwd = adminPwd;
		this.adminStatus = adminStatus;
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

	public Integer getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}
	
	@Override
	public String toString() {
	    return "AdminVO{" +
	            "adminId=" + adminId +
	            ", adminName='" + adminName + '\'' +
	            ", adminAcc='" + adminAcc + '\'' +
	            ", adminPwd='" + adminPwd + '\'' +
	            ", adminStatus=" + adminStatus +
	            '}';
	}

}