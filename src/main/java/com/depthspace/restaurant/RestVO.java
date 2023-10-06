package com.depthspace.restaurant;

public class RestVO implements java.io.Serializable{
	private Integer rest_id;
	private String rest_name;
	private String rest_tel;
	private String rest_address;
	private String rest_open;
	private Integer rest_status;
	private Integer booking_limit;
	private Integer admin_id;
	
	public Integer getRest_id() {
		return rest_id;
	}
	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
	}
	public String getRest_name() {
		return rest_name;
	}
	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}
	public String getRest_tel() {
		return rest_tel;
	}
	public void setRest_tel(String rest_tel) {
		this.rest_tel = rest_tel;
	}
	public String getRest_address() {
		return rest_address;
	}
	public void setRest_address(String rest_address) {
		this.rest_address = rest_address;
	}
	public String getRest_open() {
		return rest_open;
	}
	public void setRest_open(String rest_open) {
		this.rest_open = rest_open;
	}
	public Integer getRest_status() {
		return rest_status;
	}
	public void setRest_status(Integer rest_status) {
		this.rest_status = rest_status;
	}
	public Integer getBooking_limit() {
		return booking_limit;
	}
	public void setBooking_limit(Integer booking_limit) {
		this.booking_limit = booking_limit;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	
	
	
	
}
