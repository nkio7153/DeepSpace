package com.depthspace.tour.model.tour;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.depthspace.promotion.model.promotion.PromotionTicketView.CompositeDetail;


@Entity
@Immutable
//@Subselect("SELECT TOUR_ID, MEM_ID, TOUR_NAME, ALL_DAYS, TOUR_DESCRIPTION, TOUR_DAYS, START, END, ATTRACTIONS_NAME, ADDRESS FROM TourView")
@Table(name ="TourView3")
@IdClass(TourView.CompositeDetail.class)
public class TourView {
	@Id
	@Column(name = "TOUR_ID")
	private Integer tourId;
	
	@Id
	@Column(name="MEM_ID")
	private Integer memId;
	
	@Column(name = "TOUR_NAME")
	private String tourName;
	
	@Column(name="ALL_DAYS")
	private Integer allDays;
	
	@Column(name="TOUR_DESCRIPTION")
	private String tourDescription;	
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@Id
	@Column(name="TOUR_DAYS_ID")
	private Integer tourDaysId;
	
	@Column(name="TOUR_DAYS")
	private Integer tourDays;
	
	@Id
	@Column(name="ATTRACTIONS_ID")
	private Integer attractionsId;
	
	@Column(name = "START")
	private Timestamp start;

	@Column(name="ATTRACTIONS_NAME")
	private String attractionsName;
	
	@Column(name="ADDRESS")
	private String address;
	
	public TourView(){}

	
	
	public TourView(Integer tourId, Integer memId, String tourName, Integer allDays, String tourDescription,
			Date startDate, Date endDate, Integer tourDaysId, Integer tourDays, Integer attractionsId, Timestamp start,
			String attractionsName, String address) {
		super();
		this.tourId = tourId;
		this.memId = memId;
		this.tourName = tourName;
		this.allDays = allDays;
		this.tourDescription = tourDescription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tourDaysId = tourDaysId;
		this.tourDays = tourDays;
		this.attractionsId = attractionsId;
		this.start = start;
		this.attractionsName = attractionsName;
		this.address = address;
	}



	

//	public CompositeDetail getCompositeKey() {
//		return new CompositeDetail(tourId,memId);
//	}
//	
//	public void setCompositeKey(CompositeDetail key) {
//		this.tourId = key.getTourId();
//		this.memId = key.getMemId();
//	}
	
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



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
		return "TourView [tourId=" + tourId + ", memId=" + memId + ", tourName=" + tourName + ", allDays=" + allDays
				+ ", tourDescription=" + tourDescription + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", tourDaysId=" + tourDaysId + ", tourDays=" + tourDays + ", attractionsId=" + attractionsId
				+ ", start=" + start + ", attractionsName=" + attractionsName + ", address=" + address + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address, allDays, attractionsId, attractionsName, endDate, memId, start, startDate,
				tourDays, tourDaysId, tourDescription, tourId, tourName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TourView other = (TourView) obj;
		return Objects.equals(address, other.address) && Objects.equals(allDays, other.allDays)
				&& Objects.equals(attractionsId, other.attractionsId)
				&& Objects.equals(attractionsName, other.attractionsName) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(memId, other.memId) && Objects.equals(start, other.start)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(tourDays, other.tourDays)
				&& Objects.equals(tourDaysId, other.tourDaysId)
				&& Objects.equals(tourDescription, other.tourDescription) && Objects.equals(tourId, other.tourId)
				&& Objects.equals(tourName, other.tourName);
	}

	public static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer tourId;
		private Integer memId;
		private Integer tourDaysId;
		private Integer attractionsId;
		
		public CompositeDetail() {
	        }

		public CompositeDetail(Integer tourId, Integer memId , Integer tourDaysId , Integer attractionsId) {
			super();
			this.tourId = tourId;
			this.memId = memId;
			this.tourDaysId = tourDaysId;
			this.attractionsId = attractionsId;
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
			return Objects.hash(attractionsId, memId, tourDaysId, tourId);
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
			return Objects.equals(attractionsId, other.attractionsId) && Objects.equals(memId, other.memId)
					&& Objects.equals(tourDaysId, other.tourDaysId) && Objects.equals(tourId, other.tourId);
		}

		
		
		
	}

	
	
}
