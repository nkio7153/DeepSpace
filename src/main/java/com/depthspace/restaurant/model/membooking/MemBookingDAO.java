package com.depthspace.restaurant.model.membooking;

import java.util.List;

public interface MemBookingDAO {
	int add(MemBookingVO memBookingVO);
	int update(MemBookingVO memBookingVO);
	int delete(Integer bookingId);
	MemBookingVO findByPK(Integer bookingId);
	List<MemBookingVO> getAll();
}
