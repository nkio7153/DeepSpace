package com.depthspace.attractions.model.city.hibernate;

import java.util.List;

import com.depthspace.attractions.model.CityVO;


public interface CityDAO_Interface {
	public void insert(CityVO CityVO);
	 public void update(CityVO CityVO);
	 public void delete(Integer cityId);
	 public CityVO findByPrimaryKey(Integer cityId);
	 public List<CityVO> getAll();
}
