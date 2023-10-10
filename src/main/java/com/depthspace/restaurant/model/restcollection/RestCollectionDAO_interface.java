package com.depthspace.restaurant.model.restcollection;

import java.util.List;

public interface RestCollectionDAO_interface {
	public void insert(RestCollectionVO restCollectionVO);
	public void update(RestCollectionVO restCollectionVO);
	public void delete(Integer restId);
	public RestCollectionVO findByPrimaryKey(Integer restId);
	public List<RestCollectionVO> getAll();
}
