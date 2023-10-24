package com.depthspace.account.model.accountitem.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.account.model.accountitem.AccountItemVO;

public class AccountItemDAOImpl implements AccountItemDAO {

	// 宣告一個factory變數
	private SessionFactory factory;

	// 構造器為該物件的factory變數賦值
	public AccountItemDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	// 取得當前session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(AccountItemVO entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(AccountItemVO entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		AccountItemVO ai = getSession().get(AccountItemVO.class, id);
		if (ai != null) {
			getSession().delete(ai);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public AccountItemVO findByPrimaryKey(Integer id) {
		return getSession().get(AccountItemVO.class, id);
	}

	@Override
	public List<AccountItemVO> getAll() {
		return getSession().createQuery("from AccountItemVO", AccountItemVO.class).list();
	}

}
