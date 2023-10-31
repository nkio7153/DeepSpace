package com.depthspace.restaurant.service;

import java.util.List;

import com.depthspace.restaurant.model.restcollection.RestCollectionVO;

public interface RestcollectionService {
	RestCollectionVO add(RestCollectionVO restCollectionVO);
	void delete(RestCollectionVO restCollectionVO);
	List<RestCollectionVO> findByMemId(Integer memId);
	List<RestCollectionVO> getAll();
}
