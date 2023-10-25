package com.depthspace.account.model.account.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.account.model.account.AccountVO;

public class AccountDAOImpl implements AccountDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public AccountDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(AccountVO entity) {
		int count = (int) getSession().save(entity);
		return count;
	}

	@Override
	public int update(AccountVO entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		AccountVO account = getSession().get(AccountVO.class, id);
		// 0:失敗 1:成功
		int state;
		if (account != null) {
			getSession().delete(account);
		
			state = 1;
		} else {
			
			state = 0;
		}
		return state;
	}

	@Override
	public AccountVO getById(Integer id) {
		return getSession().get(AccountVO.class, id);
	}

	@Override
	public List<AccountVO> getAll() {
		return getSession().createQuery("from AccountVO", AccountVO.class).list();
	}
}
