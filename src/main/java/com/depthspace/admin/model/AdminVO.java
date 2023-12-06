package com.depthspace.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity // 標註該類別為 JPA 實體
@Table(name = "ADMIN") // 定義對應的數據庫表名稱為 ADMIN
public class AdminVO implements Serializable{ // 定義了一個名為 AdminVO 的類別，並實現了 Serializable 接口
	@Expose // Gson 序列化時將包含此字段
	@Id     // 標註該字段為主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 定義主鍵生成策略為資料庫 ID 自增
	@Column(name = "ADMIN_ID") // 將此字段映射到數據庫表的 ADMIN_ID 欄位
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
	
	public AdminVO() { // 定義一個無參數的構造函數
    }

	// 定義一個含所有字段的構造函數
	public AdminVO(Integer adminId, String adminName, String adminAcc, String adminPwd, Byte adminStatus,
			Byte adminVerifyStatus, Byte adminFuncName) {
		super(); // 調用父類的構造函數
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminAcc = adminAcc;
		this.adminPwd = adminPwd;
		this.adminStatus = adminStatus;
		this.adminVerifyStatus = adminVerifyStatus;
		this.adminFuncName = adminFuncName;
	}

	// 定義一個不含 adminId 的構造函數
	public AdminVO(String adminAcc, String adminPwd, String adminName, Byte adminStatus, Byte adminVerifyStatus, Byte adminFuncName) {
	    this.adminAcc = adminAcc;
	    this.adminPwd = adminPwd;
	    this.adminName = adminName;
	    this.adminStatus = adminStatus;
	    this.adminVerifyStatus = adminVerifyStatus;
	    this.adminFuncName = adminFuncName;
	}

	// 以下是各個屬性的 getter 和 setter 方法，用於獲取和設置屬性值
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

	@Override // 覆寫 toString 方法，以便於打印出該類的實例信息
	public String toString() {
		return "AdminVO [adminId=" + adminId + ", adminName=" + adminName + ", adminAcc=" + adminAcc + ", adminPwd="
				+ adminPwd + ", adminStatus=" + adminStatus + ", adminVerifyStatus=" + adminVerifyStatus
				+ ", adminFuncName=" + adminFuncName + "]";
	}
	
}