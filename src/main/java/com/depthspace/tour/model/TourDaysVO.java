package com.depthspace.tour.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TOUR_DAYS")
public class TourDaysVO implements Serializable{
	@Id
	@Column(name="TOUR_DAYS_ID")
	private Integer tourDaysId;
	
	@Column(name="TOUR_DAYS")
	private Integer tourDays;
	
	@Column(name="TOUR_ID")
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
	@Override
	public String toString() {
		return "TourDaysVO [tourDaysId=" + tourDaysId + ", tourDays=" + tourDays + ", tourId=" + tourId + "]";
	}	
}
