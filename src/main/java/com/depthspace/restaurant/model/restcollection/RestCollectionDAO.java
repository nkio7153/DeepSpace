package com.depthspace.restaurant.model.restcollection;

import java.util.List;

public interface RestCollectionDAO {
	int add(RestCollectionVO restCollectionVO);
	int delete(RestCollectionVO restCollectionVO);
	List<RestCollectionVO> findByMemId(Integer memId);
	List<RestCollectionVO> getAll();
}
