package com.depthspace.attractions.model;

import java.io.Serializable;

public class CityVO implements Serializable{
	private Integer cityId;
	private String cityName;
	public CityVO() {
		super();
	}
	public CityVO(Integer cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
