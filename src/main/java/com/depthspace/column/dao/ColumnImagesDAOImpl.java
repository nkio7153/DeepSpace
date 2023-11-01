package com.depthspace.column.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.column.model.ColumnImagesVO;

public class ColumnImagesDAOImpl implements ColumnImagesDAO {

	private SessionFactory factory;

	public ColumnImagesDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(ColumnImagesVO columnImagesVO) {
//		return (Integer) getSession().save(columnImagesVO);
		Transaction tx = null;
		try {
			tx = getSession().beginTransaction();  // 開始事務
			int id = (Integer) getSession().save(columnImagesVO);
			tx.commit();  // 提交事務
			return id;
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();  // 如果出現異常，回滾事務
			}
			throw e;  // 再次拋出異常
		}
	}

	@Override
	public void update(ColumnImagesVO columnImagesVO) {
		getSession().update(columnImagesVO);
	}

}
