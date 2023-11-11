package com.depthspace.admin.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Function {
    @Id
    @Column(name = "FUNC_ID")
    private  Integer funcId;
    @Column(name = "FUNC_NAME")
    private  String funcName;
	public Integer getFuncId() {
		return funcId;
	}
	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
    
    
}
