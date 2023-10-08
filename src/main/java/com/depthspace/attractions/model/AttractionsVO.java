package com.depthspace.attractions.model;

import java.io.Serializable;

public class AttractionsVO implements Serializable{
	private Integer attractionsId;
	private Integer attractionsTypeId;
	private Integer areaId;
	private String attractionsName;
	private String description;
	private Integer attractionsStatus;
	public AttractionsVO() {
		super();
	}
	public AttractionsVO(Integer attractionsId, Integer attractionsTypeId, Integer areaId, String attractionsName,
			String description, Integer attractionsStatus) {
		super();
		this.attractionsId = attractionsId;
		this.attractionsTypeId = attractionsTypeId;
		this.areaId = areaId;
		this.attractionsName = attractionsName;
		this.description = description;
		this.attractionsStatus = attractionsStatus;
	}
	public Integer getAttractionsId() {
		return attractionsId;
	}
	public void setAttractionsId(Integer attractionsId) {
		this.attractionsId = attractionsId;
	}
	public Integer getAttractionsTypeId() {
		return attractionsTypeId;
	}
	public void setAttractionsTypeId(Integer attractionsTypeId) {
		this.attractionsTypeId = attractionsTypeId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAttractionsName() {
		return attractionsName;
	}
	public void setAttractionsName(String attractionsName) {
		this.attractionsName = attractionsName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getAttractionsStatus() {
		return attractionsStatus;
	}
	public void setAttractionsStatus(Integer attractionsStatus) {
		this.attractionsStatus = attractionsStatus;
	}
}
