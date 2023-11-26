package com.depthspace.restaurant.model.restaurant;

import java.util.List;
import java.util.Map;

public interface RestDAO {
	int add(RestVO restVO);
	int update(RestVO restVO);
	int delete(Integer restId);
	RestVO findByPK(Integer restId);
	RestVO findByAdmin(Integer adminId);
	List<RestVO> getAll();
	List<RestVO> showRest();
	List<RestVO> sreach(Map<String, String> map);

}
