package com.depthspace.member.model.jdbc;

import java.util.List;

import com.depthspace.member.model.MemVO;

public interface MemDAO_Interface {
	//會員註冊
	 public void insert(MemVO entity);
	//會員修改
	 public void update(MemVO MemVO);
	 public void delete(Integer memId);
	 public MemVO findByPrimaryKey(Integer memId);
	 public List<MemVO> getAll();
	 public MemVO findByMemId(Integer memId);
	 public List<MemVO> getEmail(String memEmail);
}
