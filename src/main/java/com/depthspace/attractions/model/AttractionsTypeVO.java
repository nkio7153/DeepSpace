package com.depthspace.attractions.model;

import java.io.Serializable;

public class AttractionsTypeVO implements Serializable{
	private Integer attractionsTypeId;
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
	
}
