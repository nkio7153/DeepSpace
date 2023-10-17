package com.depthspace.restaurant.service;

import java.util.List;

import com.depthspace.restaurant.model.restaurant.RestVO;


public interface RestService {
	RestVO addRest(RestVO restVO);
	RestVO updateRest(RestVO restVO);
	void deleteRest(Integer restId);
	RestVO getRestByRestId(Integer restId);
	List<RestVO> getAllRest();
	

}
