package com.depthspace.tour.model;

import java.io.Serializable;

public class TourTypeVO implements Serializable{
	private Integer tourTypeId;
	private String tourTypName;
	public TourTypeVO() {
		super();
	}
	public TourTypeVO(Integer tourTypeId, String tourTypName) {
		super();
		this.tourTypeId = tourTypeId;
		this.tourTypName = tourTypName;
	}
	public Integer getTourTypeId() {
		return tourTypeId;
	}
	public void setTourTypeId(Integer tourTypeId) {
		this.tourTypeId = tourTypeId;
	}
	public String getTourTypName() {
		return tourTypName;
	}
	public void setTourTypName(String tourTypName) {
		this.tourTypName = tourTypName;
	}
	
	
}
