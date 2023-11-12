package com.depthspace.member.service;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.depthspace.member.model.MemVO;
import com.depthspace.member.model.hibernate.HibernateMemDAOImpl;
import com.depthspace.member.model.hibernate.HibernateMemDAO_Interface;
import com.depthspace.member.model.jdbc.MemDAO_Interface;
import com.depthspace.member.model.jdbc.MemJDBCDAO;

public class MemberService {
	private MemDAO_Interface dao;

	public MemberService() {
		dao = new MemJDBCDAO();
	}
	//增加會員
	public MemVO addMember(MemVO memVO) {
		dao.insert(memVO);
		return memVO;
	}
	//更新會員資料
	public MemVO updateMember(MemVO memvo) {
		dao.update(memvo);
		return memvo;
	}
	// 取得會員資訊
	public MemVO getMemberInfo(String memAcc) {
		return dao.getMemberInfo(memAcc);
	}
	//拿到所有會員資料
	public List<MemVO> getAll(){
		return dao.getAll();
	}
	public List<MemVO> getEmail(String memEmail){
		return dao.getEmail(memEmail);
	}
	
	public MemVO findByMemId(Integer memId) {
		return dao.findByMemId(memId);
	}


}
