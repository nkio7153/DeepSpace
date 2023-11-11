package com.depthspace.admin.vo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PERMISSION")
@IdClass(PermissionId.class)

public class Permission {
    @Id
    @Column(name = "ADMIN_ID")
    private Integer adminId;
    @Id
    @Column(name = "FUNC_Id")
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
}

class PermissionId implements Serializable {
    private Integer adminId;
    private Integer funcId;
}
