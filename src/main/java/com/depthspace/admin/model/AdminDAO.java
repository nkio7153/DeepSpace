package com.depthspace.admin.model;

import java.util.List;
import java.util.Map;

public interface AdminDAO {

	int insert(AdminVO entity);

	int update(AdminVO entity);
	
	int delete(AdminVO id);
	 
	AdminVO getById(Integer id);
	
	List<AdminVO> getAll();
	
	List<AdminVO> getByCompositeQuery(Map<String, String> map);
	
	List<AdminVO> getAll(int currentPage);
	
	long getTotal();
}
