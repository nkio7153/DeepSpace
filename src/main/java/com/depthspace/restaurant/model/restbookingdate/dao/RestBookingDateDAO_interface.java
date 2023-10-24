package com.depthspace.restaurant.model.restbookingdate.dao;

import java.util.List;

import com.depthspace.restaurant.model.restbookingdate.RestBookingDateVO;

public interface RestBookingDateDAO_interface {
	public void insert(RestBookingDateVO restBookingDateVO);
	public void update(RestBookingDateVO restBookingDateVO);
	public void delete(RestBookingDateVO restBookingDateVO);
	public RestBookingDateVO findByPrimaryKey(RestBookingDateVO restBookingDateVO);
	public List<RestBookingDateVO> getAll();
}
