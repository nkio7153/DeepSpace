package com.depthspace.tour.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TourDetailVO implements Serializable{
	private Integer tourDaysId;
	private Integer attractionsId;
	private Timestamp start;
	private Timestamp end;
	private String attractionsName;
	public TourDetailVO() {
		super();
	}
	public TourDetailVO(Integer tourDaysId, Integer attractionsId, Timestamp start, Timestamp end,
			String attractionsName) {
		super();
		this.tourDaysId = tourDaysId;
		this.attractionsId = attractionsId;
		this.start = start;
		this.end = end;
		this.attractionsName = attractionsName;
	}
	public Integer getTourDaysId() {
		return tourDaysId;
	}
	public void setTourDaysId(Integer tourDaysId) {
		this.tourDaysId = tourDaysId;
	}
	public Integer getAttractionsId() {
		return attractionsId;
	}
	public void setAttractionsId(Integer attractionsId) {
		this.attractionsId = attractionsId;
	}
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public String getAttractionsName() {
		return attractionsName;
	}
	public void setAttractionsName(String attractionsName) {
		this.attractionsName = attractionsName;
	}
}
