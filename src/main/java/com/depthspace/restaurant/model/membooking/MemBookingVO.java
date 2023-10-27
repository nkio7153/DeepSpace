package com.depthspace.restaurant.model.membooking;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.depthspace.restaurant.model.restaurant.RestVO;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "MEM_BOOKING")
public class MemBookingVO implements Serializable {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOKING_ID", updatable = false)
	private Integer bookingId;
	@Expose
	@Column(name = "REST_ID")
	private Integer restId;
	@Expose
	@Column(name = "MEM_ID")
	private Integer memId;
	@Expose
	@Column(name = "CHECK_STATUS")
	private Integer checkStatus;
	@Expose
	@Column(name = "BOOKING_TIME")
	private Integer bookingTime;
	@Expose
	@Column(name = "BOOKING_NUMBER")
	private Integer bookingNumber;
	@Expose
	@Column(name = "BOOKING_DATE")
	private Date bookingDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REST_ID", referencedColumnName = "REST_ID", insertable = false, updatable = false)
	private RestVO restVO;

	public RestVO getRestVO() {
		return restVO;
	}

	public void setRestVO(RestVO restVO) {
		this.restVO = restVO;
	}

	public MemBookingVO() {
		super();
	}

	public MemBookingVO(Integer bookingId, Integer restId, Integer memId, Integer checkStatus, Integer bookingTime,
			Integer bookingNumber, Date bookingDate) {
		super();
		this.bookingId = bookingId;
		this.restId = restId;
		this.memId = memId;
		this.checkStatus = checkStatus;
		this.bookingTime = bookingTime;
		this.bookingNumber = bookingNumber;
		this.bookingDate = bookingDate;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getRestId() {
		return restId;
	}

	public void setRestId(Integer restId) {
		this.restId = restId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Integer bookingTime) {
		this.bookingTime = bookingTime;
	}

	public Integer getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(Integer bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "MemBookingVO [bookingId=" + bookingId + ", restId=" + restId + ", memId=" + memId + ", checkStatus="
				+ checkStatus + ", bookingTime=" + bookingTime + ", bookingNumber=" + bookingNumber + ", bookingDate="
				+ bookingDate + "]";
	}

}
