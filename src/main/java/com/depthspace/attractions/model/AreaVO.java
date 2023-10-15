package com.depthspace.attractions.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="AREA")
public class AreaVO implements Serializable{
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Id
	@Column(name="AREA_ID")
	private Integer areaId;
	
	@Column(name="AREA_NAME")
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
	@Override
	public String toString() {
		return "AreaVO [cityId=" + cityId + ", areaId=" + areaId + ", areaName=" + areaName + "]";
	}
	
	
}
