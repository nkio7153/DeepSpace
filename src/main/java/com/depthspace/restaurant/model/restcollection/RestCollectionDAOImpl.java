package com.depthspace.restaurant.model.restcollection;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RestCollectionDAOImpl implements RestCollectionDAO {

	private SessionFactory factory;
	
	public RestCollectionDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	
	@Override
	public int add(RestCollectionVO restCollectionVO) {
		return (Integer) getSession().save(restCollectionVO);
	}

	@Override
	public int delete(RestCollectionVO restCollectionVO) {
		RestCollectionVO rc = getSession().get(RestCollectionVO.class, restCollectionVO);
		if (rc != null) {
			getSession().delete(rc);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public List<RestCollectionVO> findByMemId(Integer memId) {
		List<RestCollectionVO> list = getSession().createQuery("from RestCollectionVO where MEM_ID = :memId", RestCollectionVO.class)
										.setParameter("memId", memId)
										.list();
		return list;
	}

	@Override
	public List<RestCollectionVO> getAll() {
		return getSession().createQuery("from RestCollectionVO", RestCollectionVO.class).list();
	}
	
}
