package com.depthspace.attractions.service;

import java.util.List;

import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.attractions.hibernate.AttractionsDAOImpl;
import com.depthspace.attractions.model.attractions.hibernate.AttractionsDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class AttractionsService {
	private AttractionsDAO_Interface dao;
	
	public AttractionsService() {
		dao = new AttractionsDAOImpl(HibernateUtil.getSessionFactory());
	}
	//取得所有景點
	public List<AttractionsVO> getAllAttractions(Integer attractionsId){
		List<AttractionsVO> list = dao.getAllAttractions(attractionsId);
		return list;
		
	}
	public List<AttractionsVO> getAll() {
		List<AttractionsVO> list = dao.getAll();
		return list;
	}
	//預設為台北市，尋找台北市各個景點
	public List<AttractionsVO> findOneAttractions() {
		List<AttractionsVO> list = dao.findOneAttractions();
		return list;
		
	}
	//尋找其他景點
	public List<AttractionsVO> findOtherAttractions(String cityName) {
		
		List<AttractionsVO> list = dao.findOtherAttractions(cityName);
		return list;
	}
	//依據景點id取的該景點物件
	public AttractionsVO getAttractionsById(Integer attractionsId) {
		return dao.getAttractionsById(attractionsId);
	}
	//依據景點名稱收巡該景點集合
	public List<AttractionsVO> getAttractionsName(String attractionsName) {
		return dao.getAttractionsName(attractionsName);
	}
	
}
