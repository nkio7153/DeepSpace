package com.depthspace.admin.model.model;

import java.util.List;

public interface AdminDAO {

	int insert(AdminVO adminVO);

	int update(AdminVO adminVO);
	
	int delete(Integer id);
	 
	AdminVO getById(Integer id);
	
	List<AdminVO> getAll();
	
	List<AdminVO> getAll(int currentPage);
	
	long getTotal();
}

