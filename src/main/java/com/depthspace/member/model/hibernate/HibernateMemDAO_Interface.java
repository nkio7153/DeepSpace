package com.depthspace.member.model.hibernate;

import java.util.List;

import com.depthspace.member.model.MemVO;

public interface HibernateMemDAO_Interface {
	// 會員註冊
	int insert(MemVO entity);

	// 會員修改
	int update(MemVO entity);

	//刪除會員
	int delete(Integer memId);

	MemVO getById(Integer memId);
	
	List<MemVO> getAll();
	
	//疑問:是複合主鍵才要?
//	List<MemVO> getByCompositeQuery();

	List<MemVO> getAll(int currentPage);
}
