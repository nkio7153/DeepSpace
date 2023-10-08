package com.depthspace.attractions.model;

import java.io.Serializable;

public class AreaVO implements Serializable{
	private Integer cityId;
	private Integer areaId;
	private String areaName;
	public AreaVO() {
		super();
	}
	public AreaVO(Integer cityId, Integer areaId, String areaName) {
		super();
		this.cityId = cityId;
		this.areaId = areaId;
		this.areaName = areaName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}
