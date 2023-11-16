package com.depthspace.tour.service;

import java.util.List;

import com.depthspace.tour.model.TourDetailVO;
import com.depthspace.tour.model.tourDetail.hibernate.HbTourDetailDAOImpl;
import com.depthspace.tour.model.tourDetail.hibernate.HbTourDetailDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class TourDetailService implements HbTourDetailDAO_Interface{
private HbTourDetailDAO_Interface dao;
	
	public TourDetailService() {
		dao = new HbTourDetailDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public void insert(TourDetailVO entity) {
		dao.insert(entity);
		
	}

	@Override
	public void update(TourDetailVO entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Integer tourDaysId, Integer attractionsId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TourDetailVO findByPrimaryKey(Integer tourDaysId, Integer attractionsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TourDetailVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TourDetailVO> getTourDaysId(Integer tourDaysId) {
		return dao.getTourDaysId(tourDaysId);
	}
	
}
