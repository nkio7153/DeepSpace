package com.depthspace.tour.service;

import java.util.List;

import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tour.hibernate.HbTourDAOImpl;
import com.depthspace.tour.model.tour.hibernate.HbTourDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class TourService implements TourService_Interface{
	private HbTourDAO_Interface dao;

	public TourService() {
		dao = new HbTourDAOImpl(HibernateUtil.getSessionFactory());
	}
	@Override
	public int insert(TourVO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(TourVO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer tourId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TourVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TourVO> getByMemId(Integer memId) {
		return dao.getByMemId(memId);
		
	}
	
	
	
}
