package com.depthspace.account.model.account.service;

import java.util.List;

import com.depthspace.account.model.account.AccountVO;

public interface AccountService {
	int insert(AccountVO tod);
	int update(AccountVO tod);
    public int delete(Integer accountId);
    List<AccountVO> getbyMemId(Integer MemId);
    List<AccountVO> getAll();
}
