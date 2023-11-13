package com.depthspace.admin.model.hibernate;

import java.util.List;
import java.util.Map;

import com.depthspace.admin.model.AdminVO;

public interface HibernateAdminDAO_Interface {

	int insert(AdminVO entity);

	int update(AdminVO entity);
	
	int delete(Integer adminid);
	 
	AdminVO getById(Integer adminId);
	
	AdminVO findByAdminAcc(String adminAcc);
	
	List<AdminVO> getAll();
	
	List<AdminVO> getAll(int currentPage);

	AdminVO getById(String adminAcc);
	AdminVO findOneAdmin(String adminAcc);
}