package com.depthspace.account.model.account.service;

import java.util.List;

import com.depthspace.account.model.account.AccountVO;
import com.depthspace.account.model.account.dao.AccountDAO;
import com.depthspace.account.model.account.dao.AccountDAOImpl;
import com.depthspace.utils.HibernateUtil;

public class AccountServiceImpl implements AccountService {
	private AccountDAO dao;

	public AccountServiceImpl() {
		dao = new AccountDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public int insert(AccountVO tod) {
		return dao.insert(tod);
	}

	@Override
	public int update(AccountVO tod) {
		return dao.update(tod);
	}

	@Override
	public int delete(Integer accountId) {
		return dao.delete(accountId);
	}

	@Override
	public List<AccountVO> getbyMemId(Integer MemId) {
		return dao.getByMemId(MemId);
	}

	@Override
	public List<AccountVO> getAll() {
	    return dao.getAll();
	}
}
