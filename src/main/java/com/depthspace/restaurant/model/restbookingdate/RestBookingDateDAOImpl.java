package com.depthspace.restaurant.model.restbookingdate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RestBookingDateDAOImpl implements RestBookingDateDAO {
private SessionFactory factory;
	
	public RestBookingDateDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void add(RestBookingDateVO restBookingDateVO) {
		getSession().save(restBookingDateVO);
	}

	@Override
	public int update(RestBookingDateVO restBookingDateVO) {
		try {
			getSession().update(restBookingDateVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(RestBookingDateVO restBookingDateVO) {
		RestBookingDateVO bookingDate = getSession().get(RestBookingDateVO.class, restBookingDateVO.getCompositeKey());
		if (bookingDate != null) {
			getSession().delete(bookingDate);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public RestBookingDateVO findByPK(RestBookingDateVO restBookingDateVO) {
		return getSession().get(RestBookingDateVO.class, restBookingDateVO.getCompositeKey());
	}

	@Override
	public List<RestBookingDateVO> getAll() {
		return getSession().createQuery("from RestBookingDateVO", RestBookingDateVO.class).list();
	}

	@Override
	public int updateMorningNum(RestBookingDateVO restBookingDateVO) {
		try {
			RestBookingDateVO vo = getSession().get(RestBookingDateVO.class, restBookingDateVO.getCompositeKey());
			vo.setMorningNum(restBookingDateVO.getMorningNum());
			getSession().update(vo);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int updateNoonNum(RestBookingDateVO restBookingDateVO) {
		try {
			RestBookingDateVO vo = getSession().get(RestBookingDateVO.class, restBookingDateVO.getCompositeKey());
			vo.setNoonNum(restBookingDateVO.getNoonNum());
			getSession().update(vo);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int updateEveningNum(RestBookingDateVO restBookingDateVO) {
		try {
			RestBookingDateVO vo = getSession().get(RestBookingDateVO.class, restBookingDateVO.getCompositeKey());
			vo.setEveningNum(restBookingDateVO.getEveningNum());
			getSession().update(vo);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	
	
}
