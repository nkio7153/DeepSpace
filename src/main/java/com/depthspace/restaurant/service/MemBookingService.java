package com.depthspace.restaurant.service;

import java.util.List;

import com.depthspace.restaurant.model.membooking.MemBookingVO;

public interface MemBookingService {
	MemBookingVO add(MemBookingVO membookingVO);
	MemBookingVO update(MemBookingVO membookingVO);
	void delete(Integer Membooking);
	MemBookingVO getByMembookingId(Integer bookingId);
	List<MemBookingVO> getByRestId(Integer restId);
	List<MemBookingVO> getAllMembooking();
}
