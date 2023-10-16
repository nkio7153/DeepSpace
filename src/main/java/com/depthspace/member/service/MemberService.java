package com.depthspace.member.service;

import java.sql.Date;
import java.util.List;

import com.depthspace.member.model.MemVO;
import com.depthspace.member.model.jdbc.MemDAO_Interface;
import com.depthspace.member.model.jdbc.MemJDBCDAO;

public class MemberService {
	private MemDAO_Interface dao;

	public MemberService() {
		dao = new MemJDBCDAO();
	}

	public MemVO addMember(String memAcc, String memPwd, String memName, String memIdentity, Date memBth, byte memSex,
			String memEmail, Integer memTel, String memAdd, byte accStatus, Integer memPoint) {
		MemVO memVO = new MemVO();
		memVO.setMemAcc(memAcc);
		memVO.setMemPwd(memPwd);
		memVO.setMemName(memName);
		memVO.setMemIdentity(memIdentity);
		memVO.setMemBth(memBth);
		memVO.setMemSex(memSex);
		memVO.setMemEmail(memEmail);
		memVO.setMemTel(memTel);
		memVO.setMemAdd(memAdd);
		memVO.setAccStatus(accStatus);
		memVO.setMemPoint(memPoint);
		dao.insert(memVO);
		return memVO;
	}

	// 取得會員資訊(?)
	public MemVO addMemberInfo(Integer memId) {
		return dao.findByPrimaryKey(memId);
	}
	//
	public List<MemVO> getAll(){
		return dao.getAll();
	}

}
