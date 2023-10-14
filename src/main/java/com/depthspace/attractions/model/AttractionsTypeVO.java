package com.depthspace.attractions.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ATTRACTIONS_TYPE")
public class AttractionsTypeVO implements Serializable{
	@Id
	@Column(name="ATTRACTIONS_TYPE_ID")
	private Integer attractionsTypeId;
	
	@Column(name="TYPE_NAME")
	private String typeName;
	public AttractionsTypeVO() {
		super();
	}
	public AttractionsTypeVO(Integer attractionsTypeId, String typeName) {
		super();
		this.attractionsTypeId = attractionsTypeId;
		this.typeName = typeName;
	}
	public Integer getAttractionsTypeId() {
		return attractionsTypeId;
	}
	public void setAttractionsTypeId(Integer attractionsTypeId) {
		this.attractionsTypeId = attractionsTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "AttractionsTypeVO [attractionsTypeId=" + attractionsTypeId + ", typeName=" + typeName + "]";
	}
	
}
