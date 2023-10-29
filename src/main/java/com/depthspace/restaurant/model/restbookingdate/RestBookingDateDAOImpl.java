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
		RestBookingDateVO bookingDate = getSession().get(RestBookingDateVO.class, restBookingDateVO);
		if (bookingDate != null) {
			getSession().delete(bookingDate);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public List<RestBookingDateVO> findByPK(RestBookingDateVO restBookingDateVO) {
		String sql = "from RestBookingDateVO where REST_ID = :restId AND BOOKING_DATE = :bookingDate";
		List<RestBookingDateVO> list = getSession().createQuery(sql, RestBookingDateVO.class)
										.setParameter("restId", restBookingDateVO.getRestId())
										.setParameter("bookingDate", restBookingDateVO.getBookingDate())
										.list();
		return list;
	}

	@Override
	public List<RestBookingDateVO> getAll() {
		return getSession().createQuery("from RestBookingDateVO", RestBookingDateVO.class).list();
	}
	
	
}
