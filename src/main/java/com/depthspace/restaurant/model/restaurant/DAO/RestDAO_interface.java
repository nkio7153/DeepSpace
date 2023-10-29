package com.depthspace.restaurant.model.restaurant.dao;

import java.util.List;

import com.depthspace.restaurant.model.restaurant.RestVO;

public interface RestDAO_interface {
	public void insert(RestVO restVO);
	public void update(RestVO restVO);
	public void delete(Integer restId);
	public RestVO findByPrimaryKey(Integer restId);
	public List<RestVO> getAll();
}
