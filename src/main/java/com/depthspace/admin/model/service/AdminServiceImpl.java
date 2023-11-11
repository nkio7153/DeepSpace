package com.depthspace.admin.model.service;

import static com.depthspace.utils.Constants1.PAGE_MAX_RESULT;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import java.util.Base64.Encoder;

import com.depthspace.admin.model.model.AdminDAO;
import com.depthspace.admin.model.model.AdminDAOImpl;
import com.depthspace.admin.model.model.AdminVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.utils.HibernateUtil;
// 搭配 JSP / Thymeleaf 後端渲染畫面，將交易動作至於 view filter
public class AdminServiceImpl implements AdminService {
	// 一個 service 實體對應一個 dao 實體
	private AdminDAO dao;
	
	public AdminServiceImpl() {
		dao = new AdminDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public AdminVO addAdmin(AdminVO adminVO) {
		dao.insert(adminVO);
		return adminVO;
	}

	@Override
	public int updateAdmin(Integer adminId, String adminName, String adminAcc, String adminPwd, Integer adminStatus) {
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminId(adminId);
		adminVO.setAdminName(adminName);
		adminVO.setAdminAcc(adminAcc);
		adminVO.setAdminPwd(adminPwd);
		adminVO.setAdminStatus(adminStatus);
		
		return dao.update(adminVO);
	}

	@Override
	public void deleteAdmin(Integer adminId) {
		dao.delete(adminId);
	}

	@Override
	public AdminVO findAdminByAdminId(Integer adminId) {
		AdminVO adminVO = dao.findByAdminId(adminId);
		return adminVO;
	}

	@Override
	public List<AdminVO> getAllAdmins() {
	    return dao.getAll();
	}

	// 在 AdminServiceImpl 類中
//	@Override
//	public AdminVO findByAdminAcc(String adminAcc) {
//	    return adminDAO.findByAdminAcc(adminAcc);
//	}





}
