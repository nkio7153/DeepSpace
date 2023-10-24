package com.depthspace.account.model.accountitem.service;

import java.util.List;

import com.depthspace.account.model.accountitem.AccountItemVO;

public interface AccountItemService {
	int insert(AccountItemVO entity);

	int update(AccountItemVO entity);

	int delete(Integer id);

	public AccountItemVO findByPrimaryKey(Integer id);

	public List<AccountItemVO> getAll();
}
