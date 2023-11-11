package com.depthspace.admin.model.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.depthspace.utils.JsonIgnore;

@Entity
@Table(name = "ADMIN")

public class AdminVO {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AI鍵要加
	@Column(name = "ADMIN_ID", updatable = false , nullable = false) 
	private Integer adminId;
	@Expose
	@Column(name = "ADMIN_NAME")
	private String adminName;
	@Expose
	@Column(name = "ADMIN_ACC")
	private String adminAcc;
	@JsonIgnore
	@Expose
	@Column(name = "ADMIN_PWD")
	private String adminPwd;
	@Expose
	@Column(name = "ADMIN_STATUS", columnDefinition = "TINYINT")
	private Integer adminStatus;
	
	
	public AdminVO() {
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
		return "AdminVO [adminId=" + adminId + ", adminName=" + adminName + ", adminAcc=" + adminAcc + ", adminPwd="
				+ adminPwd + ", adminStatus=" + adminStatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminAcc, adminId, adminName, adminPwd, adminStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminVO other = (AdminVO) obj;
		return Objects.equals(adminAcc, other.adminAcc) && Objects.equals(adminId, other.adminId)
				&& Objects.equals(adminName, other.adminName) && Objects.equals(adminPwd, other.adminPwd)
				&& Objects.equals(adminStatus, other.adminStatus);
	}
	
	
}