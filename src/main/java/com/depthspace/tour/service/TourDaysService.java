package com.depthspace.tour.service;

import java.util.List;

import com.depthspace.tour.model.TourDaysVO;
import com.depthspace.tour.model.tourdays.hibernate.HbTourDaysDAOImpl;
import com.depthspace.tour.model.tourdays.hibernate.HbTourDaysDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class TourDaysService implements HbTourDaysDAO_Interface{
	private HbTourDaysDAO_Interface dao;
	
	public TourDaysService() {
		dao = new HbTourDaysDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public int insert(TourDaysVO entity) {
		dao.insert(entity);
		return 0;
	}

	@Override
	public void update(TourDaysVO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer tourDaysId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TourDaysVO findByPrimaryKey(Integer tourDaysId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TourDaysVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
