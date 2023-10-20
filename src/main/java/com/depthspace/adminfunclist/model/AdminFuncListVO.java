package com.depthspace.adminfunclist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADMIN_FUNC_LIST")

public class AdminFuncListVO {
	
	@Id
	@Column(name="ADMIN_ID")
	private Integer adminId;
	
	@Column(name="FUNC_ID")
	private Integer funcId;
	
	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getFuncId() {
		return funcId;
	}

	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}

	@Override
	public String toString() {
		return "AdminFuncListVO [adminId=" + adminId + ", funcId=" + funcId + "]";
	}
	
	
}
