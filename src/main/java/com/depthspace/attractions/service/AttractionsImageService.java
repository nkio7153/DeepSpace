package com.depthspace.attractions.service;

import java.util.List;

import com.depthspace.attractions.model.AttractionsImagesVO;
import com.depthspace.attractions.model.attractionsImages.hibernate.AttractionsImagesImpl;
import com.depthspace.attractions.model.attractionsImages.hibernate.AttractionsImages_Interface;
import com.depthspace.utils.HibernateUtil;

public class AttractionsImageService {
	private AttractionsImages_Interface dao;
	
	public AttractionsImageService() {
		dao = new AttractionsImagesImpl(HibernateUtil.getSessionFactory());
	}
	//取得一筆或多筆圖片資料
	public List<AttractionsImagesVO> getAttractionsImagesById(Integer attractionsImagesId){
		return dao.getAttractionsImagesById(attractionsImagesId);
	}
	
	public AttractionsImagesVO save(AttractionsImagesVO attrImg) {
		dao.insert(attrImg);
		return attrImg;
	}
	
	
}
