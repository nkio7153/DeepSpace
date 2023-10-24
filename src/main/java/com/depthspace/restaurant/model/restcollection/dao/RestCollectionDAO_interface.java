package com.depthspace.restaurant.model.restcollection.dao;

import java.util.List;

import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.model.restcollection.RestCollectionVO;

public interface RestCollectionDAO_interface {
	public void insert(RestCollectionVO restCollectionVO);
	public void update(Integer restId1, Integer memId1, Integer restId2, Integer memId2);
	public void delete(RestCollectionVO restCollectionVO);
	public RestCollectionVO findByPrimaryKey(RestCollectionVO restCollectionVO);
	public List<RestCollectionVO> CountfindByMemId(Integer memId);
	public List<RestCollectionVO> getAll();
}
