package com.depthspace.admin.model.service;

import java.util.List;
import java.util.Map;

import com.depthspace.admin.model.model.AdminVO;

public interface AdminService {
	
	AdminVO addAdmin(AdminVO adminVO);
	
	int updateAdmin(Integer adminId, String adminName, String adminAcc, String adminPwd, Integer adminStatus);
	
	void deleteAdmin(Integer adminId);
	
	AdminVO findAdminByAdminId(Integer adminId);
	
	List<AdminVO> getAllAdmins();
	
//	AdminVO findByAdminAcc(String adminAcc);
}