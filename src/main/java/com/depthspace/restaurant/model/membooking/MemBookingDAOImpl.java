package com.depthspace.restaurant.model.membooking;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MemBookingDAOImpl implements MemBookingDAO {
	
	private SessionFactory factory;
	
	public MemBookingDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(MemBookingVO memBookingVO) {
		return (Integer) getSession().save(memBookingVO);
	}

	@Override
	public int update(MemBookingVO memBookingVO) {
		try {
			getSession().update(memBookingVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer bookingId) {
		MemBookingVO mb = getSession().get(MemBookingVO.class, bookingId);
		if (mb != null) {
			getSession().delete(mb);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public MemBookingVO findByPK(Integer bookingId) {
		return getSession().get(MemBookingVO.class, bookingId);
	}

	@Override
	public List<MemBookingVO> findByMemID(Integer memId) {
		List<MemBookingVO> list = getSession().createQuery("from MemBookingVO mb  where MEM_ID = :MEMID", MemBookingVO.class)
									.setParameter("MEMID", memId)
									.list();
		return list;
	}
	
	@Override
	public List<MemBookingVO> findByRestID(Integer restId) {
		List<MemBookingVO> list = getSession().createQuery("from MemBookingVO mb  where REST_ID = :RESTID", MemBookingVO.class)
									.setParameter("RESTID", restId)
									.list();
		return list;
	}
	
	@Override
	public List<MemBookingVO> getAll() {
		return getSession().createQuery("from MemBookingVO", MemBookingVO.class).list();
	}
	
}
