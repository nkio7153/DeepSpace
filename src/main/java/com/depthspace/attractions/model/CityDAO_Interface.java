package com.depthspace.attractions.model;

import java.util.List;


public interface CityDAO_Interface {
	public void insert(CityVO CityVO);
	 public void update(CityVO CityVO);
	 public void delete(Integer cityId);
	 public CityVO findByPrimaryKey(Integer cityId);
	 public List<CityVO> getAll();
}
