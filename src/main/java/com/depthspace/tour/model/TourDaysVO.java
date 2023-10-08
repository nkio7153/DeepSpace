package com.depthspace.tour.model;

import java.io.Serializable;

public class TourDaysVO implements Serializable{
	private Integer tourDaysId;
	private Integer tourDays;
	private Integer tourId;
	public TourDaysVO() {
		super();
	}
	public TourDaysVO(Integer tourDaysId, Integer tourDays, Integer tourId) {
		super();
		this.tourDaysId = tourDaysId;
		this.tourDays = tourDays;
		this.tourId = tourId;
	}
	public Integer getTourDaysId() {
		return tourDaysId;
	}
	public void setTourDaysId(Integer tourDaysId) {
		this.tourDaysId = tourDaysId;
	}
	public Integer getTourDays() {
		return tourDays;
	}
	public void setTourDays(Integer tourDays) {
		this.tourDays = tourDays;
	}
	public Integer getTourId() {
		return tourId;
	}
	public void setTourId(Integer tourId) {
		this.tourId = tourId;
	}
	
	
	
}
