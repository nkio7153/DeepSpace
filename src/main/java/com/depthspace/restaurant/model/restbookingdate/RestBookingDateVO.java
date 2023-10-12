package com.depthspace.restaurant.model.restbookingdate;

import java.io.Serializable;
import java.sql.Date;

public class RestBookingDateVO implements Serializable {
	private Integer restId;
	private Date bookingDate;
	private Integer restOpen;
	private Integer morningNum;
	private Integer noonNum;
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

}
