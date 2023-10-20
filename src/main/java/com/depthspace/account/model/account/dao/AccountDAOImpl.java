package com.depthspace.account.model.account.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.account.model.account.AccountVO;

public class AccountDAOImpl implements AccountDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;
	private Session session;

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

		Transaction tx = getSession().beginTransaction();
		session = getSession();
		int count = (int) session.save(entity);
		tx.commit();
		session.close();
		return count;
	}

	@Override
	public int update(AccountVO entity) {
		Transaction tx = getSession().beginTransaction();
		session = getSession();
		try {
			session.update(entity); // 或者 session.merge(entity);
			tx.commit();
			return 1; // 成功更新，可以返回1或其他适当的标识
		} catch (Exception e) {
			tx.rollback(); // 如果出现异常，回滚事务
			e.printStackTrace(); // 可以记录异常或者进行其他处理
			return 0; // 返回0或其他标识表示更新失败
		} finally {
			session.close();
		}
	}

	@Override
	public int delete(Integer id) {
		Transaction tx = getSession().beginTransaction();
		session = getSession();
		AccountVO account = session.get(AccountVO.class, id);
		// 0:失敗 1:成功
		int state;
		if (account != null) {
			session.delete(account);
			tx.commit();
			state = 1;
		} else {
			tx.rollback();
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
		Transaction tx = getSession().beginTransaction();
		session = getSession();
		List<AccountVO> list = session.createQuery("from AccountVO", AccountVO.class).list();
		tx.commit();
		session.close();
		return list;
	}
}
