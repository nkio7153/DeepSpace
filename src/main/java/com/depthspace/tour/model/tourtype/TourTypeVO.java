package com.depthspace.tour.model.tourtype;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TOUR_TYPE")
public class TourTypeVO implements Serializable{
	@Id
	@Column(name="TOUR_TYPE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tourTypeId;
	
	@Column(name="TOUR_TYP_NAME")
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
	@Override
	public String toString() {
		return "TourTypeVO [tourTypeId=" + tourTypeId + ", tourTypName=" + tourTypName + "]";
	}
	
	
}
