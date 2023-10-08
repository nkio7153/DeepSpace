package com.depthspace.tour.model;

import java.io.Serializable;

public class TourVO implements Serializable{
	private Integer tourId;
	private Integer memId;
	private String tourName;
	private Integer tourTypeId;
	private Integer allDays;
	private String tourDescription;
	private byte[] tourImg;
	public TourVO() {
		super();
	}
	public TourVO(Integer tourId, Integer memId, String tourName, Integer tourTypeId, Integer allDays,
			String tourDescription, byte[] tourImg) {
		super();
		this.tourId = tourId;
		this.memId = memId;
		this.tourName = tourName;
		this.tourTypeId = tourTypeId;
		this.allDays = allDays;
		this.tourDescription = tourDescription;
		this.tourImg = tourImg;
	}
	public Integer getTourId() {
		return tourId;
	}
	public void setTourId(Integer tourId) {
		this.tourId = tourId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getTourName() {
		return tourName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public Integer getTourTypeId() {
		return tourTypeId;
	}
	public void setTourTypeId(Integer tourTypeId) {
		this.tourTypeId = tourTypeId;
	}
	public Integer getAllDays() {
		return allDays;
	}
	public void setAllDays(Integer allDays) {
		this.allDays = allDays;
	}
	public String getTourDescription() {
		return tourDescription;
	}
	public void setTourDescription(String tourDescription) {
		this.tourDescription = tourDescription;
	}
	public byte[] getTourImg() {
		return tourImg;
	}
	public void setTourImg(byte[] tourImg) {
		this.tourImg = tourImg;
	}
	
	
	
}
