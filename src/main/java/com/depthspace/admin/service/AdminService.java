package com.depthspace.admin.service;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.admin.model.jdbc.AdminDAO_Interface;
import com.depthspace.admin.model.jdbc.AdminJDBCDAO;
import com.depthspace.member.model.MemVO;
import com.depthspace.member.model.hibernate.HibernateMemDAOImpl;
import com.depthspace.member.model.hibernate.HibernateMemDAO_Interface;
import com.depthspace.member.model.jdbc.MemDAO_Interface;
import com.depthspace.member.model.jdbc.MemJDBCDAO;

public class AdminService {
	private AdminDAO_Interface dao;

	public AdminService() {
		dao = new AdminJDBCDAO();
	}
	//增加會員
	public AdminVO addAdmin(AdminVO adminVO) {
		dao.insert(adminVO);
		return adminVO;
	}
	//更新會員資料
	public AdminVO updateAdmin(AdminVO adminVO) {
		dao.update(adminVO);
		return adminVO;
	}
	// 取得會員資訊
	public AdminVO getAdminInfo(String adminAcc) {
		return dao.getAdminInfo(adminAcc);
	}
	//拿到所有會員資料
	public List<AdminVO> getAll(){
		return dao.getAll();
	}
	
	public AdminVO findByAdminId(Integer adminId) {
		return dao.findByAdminId(adminId);
	}

	 // 檢查帳號是否已存在
    public boolean isAccountExist(String adminAcc) {
        AdminVO adminVO = dao.getAdminInfo(adminAcc);
        return adminVO != null;
    }

}
