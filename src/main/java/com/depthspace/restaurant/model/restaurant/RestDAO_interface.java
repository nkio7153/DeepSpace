package com.depthspace.restaurant.model.restaurant;

import java.util.List;

public interface RestDAO_interface {
	public void insert(RestVO restVO);
	public void update(RestVO restVO);
	public void delete(Integer restId);
	public RestVO findByPrimaryKey(Integer restId);
	public List<RestVO> getAll();
}
