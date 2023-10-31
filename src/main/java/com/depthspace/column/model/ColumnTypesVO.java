package com.depthspace.column.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="COLUMN_TYPES")
public class ColumnTypesVO implements Serializable {
	@Id
	@Column(name="COL_TYPE_ID" ,updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer colTypeId;
	
	@Column(name="COL_TYPE_NAME")
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

	@Override
	public String toString() {
		return "ColumnTypesVO [colTypeId=" + colTypeId + ", colTypeName=" + colTypeName + "]";
	}
	

	
}
