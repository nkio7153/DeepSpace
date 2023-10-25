package com.depthspace.account.model.accountitem.service;

import java.util.List;

import com.depthspace.account.model.accountitem.AccountItemVO;
import com.depthspace.account.model.accountitem.dao.AccountItemDAO;
import com.depthspace.account.model.accountitem.dao.AccountItemDAOImpl;
import com.depthspace.utils.HibernateUtil;

public class AccountItemServiceImpl implements AccountItemService{
	private AccountItemDAO dao;
	
	public AccountItemServiceImpl() {
		dao = new AccountItemDAOImpl(HibernateUtil.getSessionFactory());
	}
	@Override
	public int insert(AccountItemVO entity) {
		return dao.insert(entity);
	}

	@Override
	public int update(AccountItemVO entity) {
		return dao.update(entity);
	}

	@Override
	public int delete(Integer id) {
		return dao.delete(id);
	}

	@Override
	public AccountItemVO findByPrimaryKey(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	@Override
	public List<AccountItemVO> getAll() {
		return dao.getAll();
	}

}
