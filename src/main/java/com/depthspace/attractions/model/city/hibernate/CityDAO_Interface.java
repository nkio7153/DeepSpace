package com.depthspace.attractions.model.city.hibernate;

import java.util.List;

import com.depthspace.attractions.model.CityVO;


public interface CityDAO_Interface {
	public void insert(CityVO entity);
	 public void update(CityVO entity);
	 public void delete(Integer cityId);
	 public CityVO findByPrimaryKey(Integer cityId);
	 public List<CityVO> getAll();
}
