package com.depthspace.admin.model.jdbc;

import java.util.List;

import com.depthspace.admin.model.AdminVO;

public interface AdminDAO_Interface {
	
	 public void insert(AdminVO adminVO);
	
	 public void update(AdminVO adminVO);
	 public void delete(Integer adminId);
	 public AdminVO findByPrimaryKey(Integer adminId);
	 public List<AdminVO> getAll();
	 public AdminVO findByAdminId(Integer adminId);
	 public AdminVO getAdminInfo(String adminAcc);

//	 public List<AdminVO> getEmail(String adminEmail);
}
