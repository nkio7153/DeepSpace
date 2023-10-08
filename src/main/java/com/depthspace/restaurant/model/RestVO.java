package com.depthspace.restaurant.model;

import java.io.Serializable;

public class RestVO implements Serializable {
	private Integer restId;
	private String restName;
	private String restTel;
	private String restAddress;
	private String restOpen;
	private Integer restStatus;
	private Integer bookingLimit;
	private Integer adminId;

	public RestVO() {
		super();
	}

	public RestVO(Integer restId, String restName, String restTel, String restAddress, String restOpen,
			Integer restStatus, Integer bookingLimit, Integer adminId) {
		super();
		this.restId = restId;
		this.restName = restName;
		this.restTel = restTel;
		this.restAddress = restAddress;
		this.restOpen = restOpen;
		this.restStatus = restStatus;
		this.bookingLimit = bookingLimit;
		this.adminId = adminId;
	}

	public Integer getRestId() {
		return restId;
	}

	public void setRestId(Integer restId) {
		this.restId = restId;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getRestTel() {
		return restTel;
	}

	public void setRestTel(String restTel) {
		this.restTel = restTel;
	}

	public String getRestAddress() {
		return restAddress;
	}

	public void setRestAddress(String restAddress) {
		this.restAddress = restAddress;
	}

	public String getRestOpen() {
		return restOpen;
	}

	public void setRestOpen(String restOpen) {
		this.restOpen = restOpen;
	}

	public Integer getRestStatus() {
		return restStatus;
	}

	public void setRestStatus(Integer restStatus) {
		this.restStatus = restStatus;
	}

	public Integer getBookingLimit() {
		return bookingLimit;
	}

	public void setBookingLimit(Integer bookingLimit) {
		this.bookingLimit = bookingLimit;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

}
