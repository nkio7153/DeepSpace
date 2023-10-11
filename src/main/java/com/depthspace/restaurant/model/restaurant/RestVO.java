package com.depthspace.restaurant.model.restaurant;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESTAURANT")
public class RestVO implements Serializable {
	@Id
	@Column(name = "REST_ID")
	private Integer restId;
	@Column(name = "REST_NAME")
	private String restName;
	@Column(name = "REST_TEL")
	private String restTel;
	@Column(name = "REST_ADDRESS")
	private String restAddress;
	@Column(name = "REST_OPEN")
	private String restOpen;
	@Column(name = "REST_STATUS", columnDefinition = "TINYINT")
	private Integer restStatus;
	@Column(name = "BOOKING_LIMIT", columnDefinition = "TINYINT")
	private Integer bookingLimit;
	@Column(name = "ADMIN_ID")
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
