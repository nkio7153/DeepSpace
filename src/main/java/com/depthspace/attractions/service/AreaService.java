package com.depthspace.attractions.service;

import java.util.List;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.model.area.hibernate.AreaDAOImpl;
import com.depthspace.attractions.model.area.hibernate.AreaDAO_Interface;
import com.depthspace.tour.model.tourtype.TourTypeVO;
import com.depthspace.utils.HibernateUtil;

public class AreaService {
	private AreaDAO_Interface dao;
	
	public AreaService(){
		dao = new AreaDAOImpl(HibernateUtil.getSessionFactory());
	}
	public AreaVO getCityList(Integer areaId) {
		return dao.findByPrimaryKey(areaId);
	}
	
	public List<AreaVO> getAll() {
		List<AreaVO> list = dao.getAll();
		return list;
	}
	public List<AreaVO> getAllArea(Integer cityId) {
		List<AreaVO> list = dao.getAllArea(cityId);
		return list;
	}
	
	
	
	
}
