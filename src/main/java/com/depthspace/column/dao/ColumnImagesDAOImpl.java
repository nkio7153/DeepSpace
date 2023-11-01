package com.depthspace.column.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
	public void insert(ColumnImagesVO columnImagesVO) {
			getSession().save(columnImagesVO);
	}
}
