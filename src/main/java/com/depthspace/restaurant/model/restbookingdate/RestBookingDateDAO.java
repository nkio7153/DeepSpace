package com.depthspace.restaurant.model.restbookingdate;

import java.util.List;

public interface RestBookingDateDAO {
	void add(RestBookingDateVO restBookingDateVO);
	int update(RestBookingDateVO restBookingDateVO);
	int delete(RestBookingDateVO restBookingDateVO);
	List<RestBookingDateVO> findByPK(RestBookingDateVO restBookingDateVO);
	List<RestBookingDateVO> getAll();
}
