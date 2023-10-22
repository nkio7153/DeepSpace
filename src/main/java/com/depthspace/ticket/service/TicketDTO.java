package com.depthspace.ticket.service;

public class TicketDTO {
	
	private Integer areaId;
	private String cityName;
	
	public TicketDTO(Integer areaId, String cityName) {
		super();
		this.areaId = areaId;
		this.cityName = cityName;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	

}
