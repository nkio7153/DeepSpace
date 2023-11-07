package com.depthspace.admin.model.service;

import java.util.List;

import com.depthspace.admin.model.model.AdminDAO;
import com.depthspace.admin.model.model.AdminVO;
import com.depthspace.utils.HibernateUtil;
import com.depthspace.admin.model.model.*;

import static com.depthspace.utils.Constants1.PAGE_MAX_RESULT;

public class AdminServiceImpl implements AdminService{

private AdminDAO dao;
	
	public AdminServiceImpl() {
		dao = new AdminDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public AdminVO addAdmin(AdminVO adminVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminVO updateAdmin(AdminVO adminVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAdmin(Integer adminId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AdminVO getAdminByAdminId(Integer adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminVO> getAllAdmin(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		
		int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
}
