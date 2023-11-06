package com.depthspace.account.model.account.dao;

import java.util.List;

import com.depthspace.account.model.account.AccountVO;

public interface AccountDAO {
	int insert(AccountVO entity);

	int update(AccountVO entity);
	
	int delete(Integer id);
	 
	List<AccountVO> getByMemId(Integer MemId);
	
	List<AccountVO> getAll();
}
