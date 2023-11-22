package com.depthspace.attractions.model.area.hibernate;

import java.util.List;

import com.depthspace.attractions.model.AreaVO;

public interface AreaDAO_Interface {
	 public void insert(AreaVO AreaVO);
	 public void update(AreaVO AreaVO);
	 public void delete(Integer areaId);
	 public AreaVO findByPrimaryKey(Integer areaId);
	 public AreaVO getOneCity(Integer areaId);
	 public List<AreaVO> getAllArea(Integer cityId);
	 List<AreaVO> getAll();
}
