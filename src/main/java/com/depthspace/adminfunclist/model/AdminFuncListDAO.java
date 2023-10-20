package com.depthspace.adminfunclist.model;

import java.util.List;
import java.util.Map;

public interface AdminFuncListDAO {
	int insert(AdminFuncListVO entity);

	int update(AdminFuncListVO entity);
	
	int delete(AdminFuncListVO id);
	 
	AdminFuncListVO getById(Integer id);
	
	List<AdminFuncListVO> getAll();
	
	List<AdminFuncListVO> getByCompositeQuery(Map<String, String> map);
	
	List<AdminFuncListVO> getAll(int currentPage);
	
	long getTotal();
}
