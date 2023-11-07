package com.depthspace.admin.model.service;

import java.util.List;

import com.depthspace.admin.model.model.AdminVO;

public interface AdminService {
	
	AdminVO addAdmin(AdminVO adminVO);
	
	AdminVO updateAdmin(AdminVO adminVO);
	
	void deleteAdmin(Integer adminId);
	
	AdminVO getAdminByAdminId(Integer adminId);
	
	List<AdminVO> getAllAdmin(int currentPage);
	
	int getPageTotal();
}
