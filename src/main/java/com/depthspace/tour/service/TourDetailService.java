package com.depthspace.tour.service;

import java.sql.Time;
import java.util.List;

import com.depthspace.tour.model.TourDetailVO;
import com.depthspace.tour.model.tourDetail.hibernate.HbTourDetailDAOImpl;
import com.depthspace.tour.model.tourDetail.hibernate.HbTourDetailDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class TourDetailService{
private HbTourDetailDAO_Interface dao;
	
	public TourDetailService() {
		dao = new HbTourDetailDAOImpl(HibernateUtil.getSessionFactory());
	}
	

	public void insert(TourDetailVO entity) {
		dao.insert(entity);
		
	}


	public void update(TourDetailVO entity) {
		dao.update(entity);
	}


	public void delete(Integer tourDaysId, Integer attractionsId) {
		dao.delete(tourDaysId, attractionsId);
		
	}


	public TourDetailVO findByPrimaryKey(Integer tourDaysId, Integer attractionsId) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<TourDetailVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<TourDetailVO> getTourDaysId(Integer tourDaysId) {
		return dao.getTourDaysId(tourDaysId);
	}


	
}
