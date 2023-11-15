package com.depthspace.adminfunclist.model;

import java.util.List;
import java.util.Map;

public interface AdminFuncListDAO {
	void insert(AdminFuncListVO entity);
	
	void delete(AdminFuncListVO.CompositeDetail id);
	
	List<AdminFuncListVO> getByAdminId(Integer adminId);
	
	boolean isCollect(Integer adminId, Integer funcId);
}
