package com.depthspace.restaurant.model.restbookingdate;

import java.util.List;

public interface RestBookingDateDAO {
	void add(RestBookingDateVO restBookingDateVO);
	int update(RestBookingDateVO restBookingDateVO);
	int delete(RestBookingDateVO restBookingDateVO);
	RestBookingDateVO findByPK(RestBookingDateVO restBookingDateVO);
	List<RestBookingDateVO> getById(Integer restId);
	List<RestBookingDateVO> getAll();
	int updateMorningNum(RestBookingDateVO restBookingDateVO);
	int updateNoonNum(RestBookingDateVO restBookingDateVO);
	int updateEveningNum(RestBookingDateVO restBookingDateVO);
}
