package com.depthspace.restaurant.model.restaurant;

import java.util.List;

public interface RestDAO {
	int add(RestVO restVO);
	int update(RestVO restVO);
	int delete(Integer restId);
	RestVO findByPK(Integer restId);
	List<RestVO> getAll();
	List<RestVO> showRest();
//	List<Object> getAllandAdmin();
}
