package com.depthspace.admin.service;

import java.util.List;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.admin.model.hibernate.HibernateAdminDAOImpl;
import com.depthspace.admin.model.hibernate.HibernateAdminDAO_Interface;
import com.depthspace.member.model.MemVO;
import com.depthspace.utils.HibernateUtil;

public class HbAdminService {
	private HibernateAdminDAO_Interface dao;
	
	public HbAdminService() {
		dao = new HibernateAdminDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	public AdminVO insert(AdminVO adminVO) {
		return dao.insert(adminVO);	
	}

	public void update(AdminVO adminVO) {
		dao.update(adminVO);
	}

	public void delete(Integer adminId) {
		dao.delete(adminId);
	}
	
	public AdminVO findByAdminAcc(String adminAcc) {
		return dao.findByAdminAcc(adminAcc);
	}
	public AdminVO getById(String adminAcc) {
		return dao.getById(adminAcc);
	}
	public AdminVO findOneAdmin(String adminAcc) {
		return dao.findOneAdmin(adminAcc);
	}
	public List<AdminVO> getAll() {
		return dao.getAll();
	}
	public AdminVO getOneAdmin(Integer adminId) {
		return dao.getOneAdmin(adminId);
	}
}
