package com.depthspace.admin.service;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.admin.model.hibernate.HibernateAdminDAOImpl;
import com.depthspace.admin.model.hibernate.HibernateAdminDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class HbAdminService {
	private HibernateAdminDAO_Interface dao;
	
	public HbAdminService() {
		dao = new HibernateAdminDAOImpl(HibernateUtil.getSessionFactory());
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
}
