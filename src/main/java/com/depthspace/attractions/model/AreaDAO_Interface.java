package com.depthspace.attractions.model;

import java.util.List;

public interface AreaDAO_Interface {
	 public void insert(AreaVO AreaVO);
	 public void update(AreaVO AreaVO);
	 public void delete(Integer areaId);
	 public AreaVO findByPrimaryKey(Integer areaId);
	 public List<AreaVO> getAll();
}
