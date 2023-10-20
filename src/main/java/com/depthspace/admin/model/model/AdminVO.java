package com.depthspace.admin.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADMIN")

public class AdminVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AI鍵要加
	@Column(name="ADMIN_ID", updatable = false)
	private Integer adminId;
	
	@Column(name="ADMIN_NAME")
	private String adminName;
	
	@Column(name="ADMIN_ACC")
	private String adminAcc;
	
	@Column(name="ADMIN_PWD")
	private String adminPwd;
	
	@Column(name="ADMIN_STATUS" , columnDefinition = "TINYINT")
	private Integer adminStatus;
	


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
		return "AdminVO [adminId=" + adminId + ", adminName=" + adminName + ", adminAcc=" + adminAcc + ", adminPwd="
				+ adminPwd + ", adminStatus=" + adminStatus + "]";
	}
		
	
}
