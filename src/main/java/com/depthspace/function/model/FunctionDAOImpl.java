package com.depthspace.function.model;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.utils.HibernateUtil;

public class FunctionDAOImpl implements FunctionDAO{

	private SessionFactory factory;
    
    public FunctionDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(FunctionVO functionVO) {
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(functionVO);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
	}

	@Override
	public void update(FunctionVO functionVO) {
		getSession().update(functionVO);
		
	}

	@Override
	public void delete(Integer funcId) {
		FunctionVO function = getSession().get(FunctionVO.class, funcId);
		getSession().delete(function);
		
	}

	@Override
	public FunctionVO findByPrimaryKey(Integer funcId) {
		return getSession().get(FunctionVO.class, funcId);
	}

	@Override
	public List<FunctionVO> getAll() {
		return getSession().createQuery("from FunctionVO", FunctionVO.class).list();
	}

    
}