package com.depthspace.attractions.service;

import java.util.List;

import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.model.city.hibernate.CityDAOImpl;
import com.depthspace.attractions.model.city.hibernate.CityDAO_Interface;
import com.depthspace.tour.model.tourtype.TourTypeVO;
import com.depthspace.utils.HibernateUtil;

public class CityService {
	private CityDAO_Interface dao;
	
	public CityService(){
		dao = new CityDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	public int insert(CityVO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(TourTypeVO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Integer tourTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<CityVO> getAll() {
		List<CityVO> list = dao.getAll();
		return list;
	}
	public CityVO findByPrimaryKey(Integer cityId) {
		CityVO cityVo = dao.findByPrimaryKey(cityId);
		return cityVo;
	}
	
	
}
