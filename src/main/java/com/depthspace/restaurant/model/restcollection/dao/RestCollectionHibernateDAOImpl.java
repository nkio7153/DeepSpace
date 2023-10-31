package com.depthspace.restaurant.model.restcollection.dao;

import java.util.List;


import org.hibernate.Session;

import com.depthspace.restaurant.model.restcollection.RestCollectionVO;
import com.depthspace.utils.HibernateUtil;


public class RestCollectionHibernateDAOImpl implements RestCollectionDAO_interface {

	@Override
	public void insert(RestCollectionVO restCollectionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(restCollectionVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	
	@Override
	public void update(Integer restId1, Integer memId1, Integer restId2, Integer memId2) {
		
	}

	@Override
	public void delete(RestCollectionVO restCollectionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(restCollectionVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public RestCollectionVO findByPrimaryKey(RestCollectionVO restCollectionVO) {
		return null;
	}

	public List<RestCollectionVO> CountfindByMemId(Integer memId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			List<RestCollectionVO> list = session.createQuery("from RestCollectionVO where mem_id = :mem_id", RestCollectionVO.class)
					.setParameter("mem_id", memId)
					.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	@Override
	public List<RestCollectionVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<RestCollectionVO> list = session.createQuery("FROM RestCollectionVO", RestCollectionVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	
}
