package com.depthspace.restaurant.model.restbookingdate;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "RESTAURANT_BOOKINGDATE")
@IdClass(RestBookingDateVO.CompositeDetail.class)
public class RestBookingDateVO implements Serializable {
	@Id
	@Column(name = "REST_ID")
	private Integer restId;
	@Id
	@Column(name = "BOOKING_DATE")
	private Date bookingDate;
	@Column(name = "REST_OPEN")
	private Integer restOpen;
	@Column(name = "MORNING_NUM")
	private Integer morningNum;
	@Column(name = "NOON_NUM")
	private Integer noonNum;
	@Column(name = "EVENING_NUM")
	private Integer eveningNum;

	public RestBookingDateVO() {
		super();
	}

	public RestBookingDateVO(Integer restId, Date bookingDate, Integer restOpen, Integer morningNum, Integer noonNum,
			Integer eveningNum) {
		super();
		this.restId = restId;
		this.bookingDate = bookingDate;
		this.restOpen = restOpen;
		this.morningNum = morningNum;
		this.noonNum = noonNum;
		this.eveningNum = eveningNum;
	}

	public Integer getRestId() {
		return restId;
	}

	public void setRestId(Integer restId) {
		this.restId = restId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Integer getRestOpen() {
		return restOpen;
	}

	public void setRestOpen(Integer restOpen) {
		this.restOpen = restOpen;
	}

	public Integer getMorningNum() {
		return morningNum;
	}

	public void setMorningNum(Integer morningNum) {
		this.morningNum = morningNum;
	}

	public Integer getNoonNum() {
		return noonNum;
	}

	public void setNoonNum(Integer noonNum) {
		this.noonNum = noonNum;
	}

	public Integer getEveningNum() {
		return eveningNum;
	}

	public void setEveningNum(Integer eveningNum) {
		this.eveningNum = eveningNum;
	}

	@Override
	public String toString() {
		return "RestBookingDateVO [restId=" + restId + ", bookingDate=" + bookingDate + ", restOpen=" + restOpen
				+ ", morningNum=" + morningNum + ", noonNum=" + noonNum + ", eveningNum=" + eveningNum + "]";
	}
	
	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private Integer restId;
		private Date bookingDate;
		
		public CompositeDetail() {
			super();
		}

		public CompositeDetail(Integer restId, Date bookingDate) {
			super();
			this.restId = restId;
			this.bookingDate = bookingDate;
		}

		public Integer getRestId() {
			return restId;
		}

		public void setRestId(Integer restId) {
			this.restId = restId;
		}

		public Date getBookingDate() {
			return bookingDate;
		}

		public void setBookingDate(Date bookingDate) {
			this.bookingDate = bookingDate;
		}

		@Override
		public int hashCode() {
			return Objects.hash(bookingDate, restId);
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
			return Objects.equals(bookingDate, other.bookingDate) && Objects.equals(restId, other.restId);
		}
		
		
		
		
	}
	
}
