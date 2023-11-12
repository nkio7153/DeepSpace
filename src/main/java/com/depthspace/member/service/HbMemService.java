package com.depthspace.member.service;

import com.depthspace.member.model.MemVO;
import com.depthspace.member.model.hibernate.HibernateMemDAOImpl;
import com.depthspace.member.model.hibernate.HibernateMemDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class HbMemService {
	private HibernateMemDAO_Interface dao;
	
	public HbMemService() {
		dao = new HibernateMemDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	public MemVO findByMemAcc(String memAcc) {
		return dao.findByMemAcc(memAcc);
	}
	public MemVO getById(String memAcc) {
		return dao.getById(memAcc);
	}
	public MemVO findOneMem(String memAcc) {
		return dao.findOneMem(memAcc);
	}
}
