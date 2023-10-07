package com.depthspace.column.model;

import java.io.Serializable;

public class ColumnTypesVO implements Serializable {
	
	private Integer colTypeId;
	private String colTypeName;
	
	public ColumnTypesVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ColumnTypesVO(Integer colTypeId, String colTypeName) {
		super();
		this.colTypeId = colTypeId;
		this.colTypeName = colTypeName;
	}

	public Integer getColTypeId() {
		return colTypeId;
	}

	public void setColTypeId(Integer colTypeId) {
		this.colTypeId = colTypeId;
	}

	public String getColTypeName() {
		return colTypeName;
	}

	public void setColTypeName(String colTypeName) {
		this.colTypeName = colTypeName;
	}


	
}
