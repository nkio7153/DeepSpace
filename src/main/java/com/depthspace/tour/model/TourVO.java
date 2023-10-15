package com.depthspace.tour.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TOUR")
public class TourVO implements Serializable{
	@Id
	@Column(name="TOUR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tourId;
	
	@Column(name="MEM_ID")
	private Integer memId;
	
	@Column(name="TOUR_NAME")
	private String tourName;
	
	@Column(name="TOUR_TYPE_ID")
	private Integer tourTypeId;
	
	@Column(name="ALL_DAYS")
	private Integer allDays;
	
	@Column(name="TOUR_DESCRIPTION")
	private String tourDescription;
	
	@Column(name="TOUR_IMG" , columnDefinition = "MEDIUMBLOB")
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
	@Override
	public String toString() {
		return "TourVO [tourId=" + tourId + ", memId=" + memId + ", tourName=" + tourName + ", tourTypeId=" + tourTypeId
				+ ", allDays=" + allDays + ", tourDescription=" + tourDescription + ", tourImg="
				+ Arrays.toString(tourImg) + "]";
	}
}
