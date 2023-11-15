package com.depthspace.adminfunclist.service;

import java.util.List;

import com.depthspace.adminfunclist.model.AdminFuncListDAO;
import com.depthspace.adminfunclist.model.AdminFuncListDAOImpl;
import com.depthspace.adminfunclist.model.AdminFuncListVO;
import com.depthspace.forum.model.articlescollect.ArticlesCollectVO;
import com.depthspace.forum.model.articlescollect.dao.ArticlesCollectDAO;
import com.depthspace.forum.model.articlescollect.dao.ArticlesCollectDAOImpl;
import com.depthspace.utils.HibernateUtil;

public class AdminFuncListServiceImpl implements AdminFuncListService{
	
	private AdminFuncListDAO dao;
	
	public AdminFuncListServiceImpl() {
		dao = new AdminFuncListDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public void insert(AdminFuncListVO tod) {
		dao.insert(tod);
	}

	@Override
	public void delete(AdminFuncListVO tod) {		
		 dao.delete(new AdminFuncListVO.CompositeDetail(tod.getAdminId(),tod.getFuncId()));
	}

	@Override
	public List<AdminFuncListVO> getByAdminId(Integer adminId) {
		return dao.getByAdminId(adminId);
	}

	@Override
	public boolean isCollect(Integer adminId, Integer funcId) {
		return dao.isCollect(adminId, funcId);
	}

}
