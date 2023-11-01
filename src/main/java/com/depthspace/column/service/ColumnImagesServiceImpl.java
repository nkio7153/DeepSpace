package com.depthspace.column.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.depthspace.column.dao.ColumnImagesDAO;
import com.depthspace.column.model.ColumnImagesVO;

public class ColumnImagesServiceImpl implements ColumnImagesService{
	
//	private ColumnImagesDAO dao;
	private Session session;
	
	public ColumnImagesServiceImpl(Session session) {
		this.session = session;
	}
	
	@Override
	public void save(ColumnImagesVO colImg) {
//		dao.save(colImg);
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(colImg);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

}
