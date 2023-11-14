package com.depthspace.admin.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN")
public class AdminVO implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AI鍵要加
	@Column(name = "ADMIN_ID") 
	private Integer adminId;
	@Column(name = "ADMIN_NAME")
	private String adminName;
	@Column(name = "ADMIN_ACC")
	private String adminAcc;
	@Column(name = "ADMIN_PWD")
	private String adminPwd;
	@Column(name = "ADMIN_STATUS")
	private Byte adminStatus;
	
	
	public AdminVO() {
    }
	
	

	

	public AdminVO(Integer adminId, String adminName, String adminAcc, String adminPwd, Byte adminStatus) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminAcc = adminAcc;
		this.adminPwd = adminPwd;
		this.adminStatus = adminStatus;
	}
	
	





	public AdminVO(String adminName, String adminAcc, String adminPwd, Byte adminStatus) {
		super();
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





	public Byte getAdminStatus() {
		return adminStatus;
	}





	public void setAdminStatus(Byte adminStatus) {
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