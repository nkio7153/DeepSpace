package com.depthspace.restaurant.model.restbookingdate;

import java.sql.Date;
import java.util.List;

public interface RestBookingDateDAO_interface {
	public void insert(RestBookingDateVO restBookingDateVO);
	public void update(RestBookingDateVO restBookingDateVO);
	public void delete(RestBookingDateVO restBookingDateVO);
	public RestBookingDateVO findByPrimaryKey(RestBookingDateVO restBookingDateVO);
	public List<RestBookingDateVO> getAll();
}
