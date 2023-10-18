package com.depthspace.function.model;

import java.util.List;
import java.util.Map;

public interface FunctionDAO {

	int insert(FunctionVO entity);

	int update(FunctionVO entity);
	
	int delete(FunctionVO id);
	 
	FunctionVO getById(Integer id);
	
	List<FunctionVO> getAll();
	
	List<FunctionVO> getByCompositeQuery(Map<String, String> map);
	
	List<FunctionVO> getAll(int currentPage);

	long getTotal();
}
