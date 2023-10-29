package com.depthspace.tour.model.tour;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.depthspace.promotion.model.promotion.PromotionTicketView.CompositeDetail;


@Entity
@Immutable
@Subselect("SELECT TOUR_ID, MEM_ID, TOUR_NAME, ALL_DAYS, TOUR_DESCRIPTION, TOUR_DAYS, START, END, ATTRACTIONS_NAME, ADDRESS FROM TourView")
@Table(name ="TourView")
@IdClass(TourView.CompositeDetail.class)
public class TourView {
	@Id
	@Column(name = "TOUR_ID")
	private String tourId;
	
	@Id
	@Column(name="MEM_ID")
	private Integer memId;
	
	@Column(name = "TOUR_NAME")
	private String tourName;
	
	@Column(name="ALL_DAYS")
	private Integer allDays;
	
	@Column(name="TOUR_DESCRIPTION")
	private String tourDescription;
	
	@Column(name="TOUR_DAYS")
	private Integer tourDays;
	
	@Column(name = "START")
	private Timestamp start;

	@Column(name = "END")
	private Timestamp end;
	
	@Column(name="ATTRACTIONS_NAME")
	private String attractionsName;
	
	@Column(name="ADDRESS")
	private String address;
	
	public TourView(){}

	public TourView(String tourName, Integer allDays, String tourDescription, Integer tourDays, Timestamp start,
			Timestamp end, String attractionsName, String address) {
		super();
		this.tourName = tourName;
		this.allDays = allDays;
		this.tourDescription = tourDescription;
		this.tourDays = tourDays;
		this.start = start;
		this.end = end;
		this.attractionsName = attractionsName;
		this.address = address;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
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

	public Integer getTourDays() {
		return tourDays;
	}

	public void setTourDays(Integer tourDays) {
		this.tourDays = tourDays;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "TourView [tourName=" + tourName + ", allDays=" + allDays + ", tourDescription=" + tourDescription
				+ ", tourDays=" + tourDays + ", start=" + start + ", end=" + end + ", attractionsName="
				+ attractionsName + ", address=" + address + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TourView that = (TourView) obj;
        
		return Objects.equals(tourName, that.tourName) && Objects.equals(allDays, that.allDays) &&
		           Objects.equals(tourDescription, that.tourDescription) &&
		           Objects.equals(tourDays, that.tourDays) &&
		           Objects.equals(start, that.start) &&
		           Objects.equals(end, that.end) &&
		           Objects.equals(attractionsName, that.attractionsName) &&
		           Objects.equals(address, that.address);
	};
	
	
	@Override
	public int hashCode() {
	    return Objects.hash(tourName, allDays, tourDescription, tourDays, start, end, attractionsName, address);
	}
	
	public static class CompositeDetail implements Serializable {
		private String tourId;
		private Integer memId;
		
		public CompositeDetail() {
	        }

		public CompositeDetail(String tourId, Integer memId) {
			super();
			this.tourId = tourId;
			this.memId = memId;
		}

		@Override
		public int hashCode() {
			 return Objects.hash(tourId,memId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            CompositeDetail that = (CompositeDetail) obj;
            return Objects.equals(this.tourId,that.tourId) && Objects.equals(this.memId , that.memId);
		}
		
		
		
	}

	
	
}
