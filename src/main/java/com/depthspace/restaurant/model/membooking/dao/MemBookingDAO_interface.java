package com.depthspace.restaurant.model.membooking.dao;

import java.util.List;

import com.depthspace.restaurant.model.membooking.MemBookingVO;

public interface MemBookingDAO_interface {
	public void insert(MemBookingVO memBookingVO);
	public void update(MemBookingVO memBookingVO);
	public void delete(Integer bookingId);
	public MemBookingVO findByPrimaryKey(Integer bookingId);
	public List<MemBookingVO> getAll();
}
