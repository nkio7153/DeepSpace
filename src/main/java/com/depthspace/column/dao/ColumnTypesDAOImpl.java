package com.depthspace.column.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.depthspace.column.model.*;

public class ColumnTypesDAOImpl implements ColumnTypesDAO {

	private SessionFactory factory;

	public ColumnTypesDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(ColumnTypesVO columnTypesVO) {
		return (Integer) getSession().save(columnTypesVO); 
	}

	@Override
	public void update(ColumnTypesVO columnTypesVO) {
		getSession().update(columnTypesVO);
	}

	@Override
	public List<ColumnTypesVO> getAll() {
		return getSession().createQuery("from ColumnTypesVO", ColumnTypesVO.class).list();
	}
	
	@Override
	public ColumnTypesVO getOneById(Integer colTypeId) {
		return getSession().get(ColumnTypesVO.class, colTypeId);
	}	
	
}
