package com.depthspace.function.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FUNCTION")
public class FunctionVO {

	@Id
	@Column(name="FUNC_ID")
	private Integer funcId;
	
	@Column(name="FUNC_NAME")
	private String funcName;

	public FunctionVO() {
		super();
	}

	public FunctionVO(Integer funcId, String funcName) {
		super();
		this.funcId = funcId;
		this.funcName = funcName;
	}

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

	@Override
	public String toString() {
		return "FunctionVO [funcId=" + funcId + ", funcName=" + funcName + "]";
	}

}
