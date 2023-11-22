package com.depthspace.attractions.service;

import java.util.List;

import com.depthspace.attractions.model.AttractionsTypeVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.model.AttractionsType.hibernate.HbAttractionsTypeDAOImpl;
import com.depthspace.utils.HibernateUtil;

public class AttractionsTypeService {
	private HbAttractionsTypeDAOImpl dao;
	
	public AttractionsTypeService() {
		dao = new HbAttractionsTypeDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	public List<AttractionsTypeVO> getOneById(Integer attractionsTypeId) {
		List<AttractionsTypeVO> attr = dao.getOneById(attractionsTypeId);
		return attr;
	}
	
	public AttractionsTypeVO getOne(Integer attractionsTypeId) {
		AttractionsTypeVO attr = dao.getOne(attractionsTypeId);
		return attr;
	}
	
	public List<AttractionsTypeVO> getAll() {
		List<AttractionsTypeVO> list = dao.getAll();
		return list;
	}
	
	
}
