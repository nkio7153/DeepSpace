package com.depthspace.adminfunclist.service;

import java.util.List;

import com.depthspace.adminfunclist.model.AdminFuncListVO;

public interface AdminFuncListService {
	void insert(AdminFuncListVO tod);

	void delete(AdminFuncListVO tod);

	List<AdminFuncListVO> getByAdminId(Integer adminId);

	boolean isCollect(Integer adminId, Integer funcId);
}
