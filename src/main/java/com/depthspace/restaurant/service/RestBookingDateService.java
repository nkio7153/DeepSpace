package com.depthspace.restaurant.service;

import java.util.List;

import com.depthspace.restaurant.model.restbookingdate.RestBookingDateVO;

public interface RestBookingDateService {
	RestBookingDateVO add(RestBookingDateVO restBookingDateVO);
	RestBookingDateVO update(RestBookingDateVO restBookingDateVO);
	void delete(RestBookingDateVO restBookingDateVO);
	RestBookingDateVO findByPK(RestBookingDateVO restBookingDateVO);
	List<RestBookingDateVO> getAll();
}
