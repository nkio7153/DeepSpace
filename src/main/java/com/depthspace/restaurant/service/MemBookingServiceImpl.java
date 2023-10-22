package com.depthspace.restaurant.service;

import java.util.List;

import com.depthspace.restaurant.model.membooking.MemBookingDAO;
import com.depthspace.restaurant.model.membooking.MemBookingDAOImpl;
import com.depthspace.restaurant.model.membooking.MemBookingVO;
import com.depthspace.utils.HibernateUtil;

public class MemBookingServiceImpl implements MemBookingService {
	
	private MemBookingDAO dao;
	
	public MemBookingServiceImpl() {
		dao = new MemBookingDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public MemBookingVO add(MemBookingVO membookingVO) {
		dao.add(membookingVO);
		return membookingVO;
	}

	@Override
	public MemBookingVO update(MemBookingVO membookingVO) {
		dao.update(membookingVO);
		return membookingVO;
	}

	@Override
	public void delete(Integer Membooking) {
		dao.delete(Membooking);
	}

	@Override
	public MemBookingVO getByMembookingId(Integer bookingId) {
		return dao.findByPK(bookingId);
	}

	@Override
	public List<MemBookingVO> getByRestId(Integer restId) {
		return dao.findByRestID(restId);
	}

	@Override
	public List<MemBookingVO> getAllMembooking() {
		return dao.getAll();
	}
	
	
	
}
