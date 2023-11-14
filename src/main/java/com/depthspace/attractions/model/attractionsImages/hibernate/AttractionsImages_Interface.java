package com.depthspace.attractions.model.attractionsImages.hibernate;

import java.util.List;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.AttractionsImagesVO;
import com.depthspace.attractions.model.AttractionsVO;

public interface AttractionsImages_Interface {
	public void insert(AttractionsImagesVO AttractionsImagesVO);
	 public void update(AttractionsImagesVO AttractionsImagesVO);
	 public void delete(Integer attractionsImagesId);
	 public AttractionsImagesVO findByPrimaryKey(Integer attractionsImagesId);
	 public List<AttractionsImagesVO> getAll();
	 
	 //根據景點Id取得一或多筆資料
	 public List<AttractionsImagesVO> getAttractionsImagesById(Integer attractionsImagesId);

}