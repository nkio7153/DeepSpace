package com.depthspace.restaurant.model.restbookingdate;

import java.util.List;

public interface RestBookingDateDAO {
	int add(RestBookingDateVO restBookingDateVO);
	int update(RestBookingDateVO restBookingDateVO);
	int delete(RestBookingDateVO restBookingDateVO);
	RestBookingDateVO findByPK(RestBookingDateVO restBookingDateVO);
	List<RestBookingDateVO> getAll();
}
