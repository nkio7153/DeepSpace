package com.depthspace.tour.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.depthspace.tour.model.TourDetailVO.CompositeDetail;

@Entity
@Table(name="TOUR_DETAIL")
@IdClass(TourDetailVO.CompositeDetail.class)
public class TourDetailVO implements Serializable{
	@Id
	@Column(name="TOUR_DAYS_ID")
	private Integer tourDaysId;
	@Id
	@Column(name="ATTRACTIONS_ID")
	private Integer attractionsId;
	
	@Column(name="START")
	private Time start;
	
	@Column(name="END")
	private Timestamp end;
	
	@Column(name="ATTRACTIONS_NAME")
	private String attractionsName;
	public TourDetailVO() {
		super();
	}
	public TourDetailVO(Integer tourDaysId, Integer attractionsId, Time start, Timestamp end,
			String attractionsName) {
		super();
		this.tourDaysId = tourDaysId;
		this.attractionsId = attractionsId;
		this.start = start;
		this.end = end;
		this.attractionsName = attractionsName;
	}
	
	public CompositeDetail getCompositeKey() {
		return new CompositeDetail(tourDaysId , attractionsId);
		}
	public void setCompositeKey(CompositeDetail key) {
		this.tourDaysId = key.getTourDaysId();
		this.attractionsId = key.getAttractionsId();
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
	public Time getStart() {
		return start;
	}
	public void setStart(Time start) {
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
	@Override
	public String toString() {
		return "TourDetailVO [tourDaysId=" + tourDaysId + ", attractionsId=" + attractionsId + ", start=" + start
				+ ", end=" + end + ", attractionsName=" + attractionsName + "]";
	}
	
	
	public static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer tourDaysId;
		private Integer attractionsId;
		public CompositeDetail() {
			super();
		}
		public CompositeDetail(Integer tourDaysId, Integer attractionsId) {
			super();
			this.tourDaysId = tourDaysId;
			this.attractionsId = attractionsId;
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
		@Override
		public int hashCode() {
			return Objects.hash(attractionsId, tourDaysId);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeDetail other = (CompositeDetail) obj;
			return Objects.equals(attractionsId, other.attractionsId) && Objects.equals(tourDaysId, other.tourDaysId);
		}
		
	}
	
}
