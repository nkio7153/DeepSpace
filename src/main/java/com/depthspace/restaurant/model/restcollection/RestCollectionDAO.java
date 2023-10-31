package com.depthspace.restaurant.model.restcollection;

import java.util.List;

public interface RestCollectionDAO {
	void add(RestCollectionVO restCollectionVO);
	void delete(RestCollectionVO restCollectionVO);
	List<RestCollectionVO> findByMemId(Integer memId);
	List<RestCollectionVO> getAll();
}
