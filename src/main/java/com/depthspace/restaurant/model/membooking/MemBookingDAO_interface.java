package com.depthspace.restaurant.model.membooking;

import java.util.List;

public interface MemBookingDAO_interface {
	public void insert(MemBookingVO memBookingVO);
	public void update(MemBookingVO memBookingVO);
	public void delete(Integer bookingId);
	public MemBookingVO findByPrimaryKey(Integer bookingId);
	public List<MemBookingVO> getAll();
}
