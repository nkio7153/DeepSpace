package com.depthspace.restaurant.service;

import java.util.List;

import com.depthspace.restaurant.model.restbookingdate.RestBookingDateVO;

public interface RestBookingDateService {
	RestBookingDateVO add(RestBookingDateVO restBookingDateVO);
	RestBookingDateVO update(RestBookingDateVO restBookingDateVO);
	void delete(RestBookingDateVO restBookingDateVO);
	void deleteForRestId(Integer restId);
	RestBookingDateVO findByPK(RestBookingDateVO restBookingDateVO);
	List<RestBookingDateVO> getById(Integer restId);
	List<RestBookingDateVO> getAll();
	int updateMorningNum(RestBookingDateVO restBookingDateVO);
	int updateNoonNum(RestBookingDateVO restBookingDateVO);
	int updateEveningNum(RestBookingDateVO restBookingDateVO);
}
