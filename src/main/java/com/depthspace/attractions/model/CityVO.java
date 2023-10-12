package com.depthspace.attractions.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="CITY")
public class CityVO implements Serializable{
	@Id
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Column(name="CITY_NAME")
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
	@Override
	public String toString() {
		return "CityVO [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
	
}
