package com.depthspace.admin.model.hibernate;

import java.util.List;
import java.util.Map;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.member.model.MemVO;

public interface HibernateAdminDAO_Interface {

	AdminVO insert(AdminVO entity);

	int update(AdminVO entity);
//	int update2(AdminVO entity);
	
	int delete(Integer adminid);
	 
	AdminVO getById(Integer adminId);
	
	AdminVO findByAdminAcc(String adminAcc);
	
	List<AdminVO> getAll();
	
	List<AdminVO> getAll(int currentPage);

	AdminVO getById(String adminAcc);
	AdminVO findOneAdmin(String adminAcc);
	
	AdminVO getOneAdmin(Integer adminId);
	
	int updateStatus(Integer adminId , byte status);

	List<AdminVO> searchAdmins(String adminName);
}