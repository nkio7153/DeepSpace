package com.depthspace.restaurant.model.restaurant;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.depthspace.restaurant.model.membooking.MemBookingVO;
import com.depthspace.restaurant.model.restcollection.RestCollectionVO;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "RESTAURANT")
public class RestVO implements Serializable {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REST_ID", insertable = false, updatable = false)
	private Integer restId;
	@Expose
	@Column(name = "REST_NAME")
	private String restName;
	@Expose
	@Column(name = "REST_TEL")
	private String restTel;
	@Expose
	@Column(name = "REST_ADDRESS")
	private String restAddress;
	@Expose
	@Column(name = "REST_TYPE")
	private String restType;
	@Expose
	@Column(name = "REST_OPEN")
	private String restOpen;
	@Expose
	@Column(name = "REST_STATUS")
	private Integer restStatus;
	@Expose
	@Column(name = "BOOKING_LIMIT")
	private Integer bookingLimit;
	@Expose
	@Column(name = "ADMIN_ID")
	private Integer adminId;
	
	@OneToMany(mappedBy = "restVO", cascade = CascadeType.ALL)
	private Set<RestCollectionVO> rests;
	@OneToMany(mappedBy = "restVO", cascade = CascadeType.ALL)
	private Set<MemBookingVO> memBooking;
	
	public Set<RestCollectionVO> getRests() {
		return rests;
	}

	public void setRests(Set<RestCollectionVO> rests) {
		this.rests = rests;
	}

	public Set<MemBookingVO> getMemBooking() {
		return memBooking;
	}

	public void setMemBooking(Set<MemBookingVO> memBooking) {
		this.memBooking = memBooking;
	}

	public RestVO() {
		super();
	}

	public RestVO(Integer restId, String restName, String restTel, String restAddress, String restType ,String restOpen,
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

	public String getRestType() {
		return restType;
	}

	public void setRestType(String restType) {
		this.restType = restType;
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

	@Override
	public String toString() {
		return "RestVO [restId=" + restId + ", restName=" + restName + ", restTel=" + restTel + ", restAddress="
				+ restAddress + ", restType=" + restType + ", restOpen=" + restOpen + ", restStatus=" + restStatus + ", bookingLimit="
				+ bookingLimit + ", adminId=" + adminId + "]";
	}
}
