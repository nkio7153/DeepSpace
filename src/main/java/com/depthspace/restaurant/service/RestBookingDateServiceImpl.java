package com.depthspace.restaurant.service;

import java.util.List;

import com.depthspace.restaurant.model.restbookingdate.RestBookingDateDAO;
import com.depthspace.restaurant.model.restbookingdate.RestBookingDateDAOImpl;
import com.depthspace.restaurant.model.restbookingdate.RestBookingDateVO;
import com.depthspace.utils.HibernateUtil;

public class RestBookingDateServiceImpl implements RestBookingDateService {
	
	private RestBookingDateDAO dao;
	
	public RestBookingDateServiceImpl() {
		dao = new RestBookingDateDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public RestBookingDateVO add(RestBookingDateVO restBookingDateVO) {
		dao.add(restBookingDateVO);
		return restBookingDateVO;
	}

	@Override
	public RestBookingDateVO update(RestBookingDateVO restBookingDateVO) {
		dao.update(restBookingDateVO);
		return restBookingDateVO;
	}

	@Override
	public void delete(RestBookingDateVO restBookingDateVO) {
		dao.delete(restBookingDateVO);
		
	}

	@Override
	public RestBookingDateVO findByPK(RestBookingDateVO restBookingDateVO) {
		return dao.findByPK(restBookingDateVO);
	}

	@Override
	public List<RestBookingDateVO> getAll() {
		return dao.getAll();
	}
	
	
}
