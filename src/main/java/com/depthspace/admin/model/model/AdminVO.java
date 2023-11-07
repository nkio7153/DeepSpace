package com.depthspace.admin.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN")

public class AdminVO {

	public enum AdminStatus {
		NO_ACCESS(0), GENERAL_ADMIN(1), RESTAURANT_ADMIN(2), SUPER_ADMIN(99);

		private final int value;

		AdminStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		// 添加一個從int到enum的轉換方法
		public static AdminStatus fromInt(int value) {
			for (AdminStatus status : AdminStatus.values()) {
				if (status.getValue() == value) {
					return status;
				}
			}
			throw new IllegalArgumentException("No constant with value " + value + " found");
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AI鍵要加
	@Column(name = "ADMIN_ID", updatable = false)
	private Integer adminId;

	@Column(name = "ADMIN_NAME")
	private String adminName;

	@Column(name = "ADMIN_ACC")
	private String adminAcc;

	@Column(name = "ADMIN_PWD")
	private String adminPwd;

	@Column(name = "ADMIN_STATUS", columnDefinition = "TINYINT")
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

	public AdminStatus getAdminStatusEnum() {
		return AdminStatus.fromInt(this.adminStatus);
	}

	public void setAdminStatusEnum(AdminStatus adminStatus) {
		this.adminStatus = adminStatus.getValue();
	}
}
