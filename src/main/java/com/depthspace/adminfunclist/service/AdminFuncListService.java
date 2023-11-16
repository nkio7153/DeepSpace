package com.depthspace.adminfunclist.service;

import java.util.List;

import com.depthspace.adminfunclist.model.AdminFuncListVO;

public interface AdminFuncListService {
	void insert(AdminFuncListVO tod);

	void delete(AdminFuncListVO tod);
	
	void update(AdminFuncListVO tod);

	List<AdminFuncListVO> getByAdminId(Integer adminId);

	boolean hasPermission(Integer adminId, Integer funcId);
}
