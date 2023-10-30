package com.depthspace.restaurant.service;

import java.util.List;

import com.depthspace.restaurant.model.restcollection.RestCollectionDAO;
import com.depthspace.restaurant.model.restcollection.RestCollectionDAOImpl;
import com.depthspace.restaurant.model.restcollection.RestCollectionVO;
import com.depthspace.utils.HibernateUtil;

public class RestcollectionServiceImpl implements RestcollectionService {
	
	private RestCollectionDAO dao;
	
	public RestcollectionServiceImpl() {
		dao = new RestCollectionDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public RestCollectionVO add(RestCollectionVO restCollectionVO) {
		dao.add(restCollectionVO);
		return restCollectionVO;
	}

	@Override
	public void delete(RestCollectionVO restCollectionVO) {
		dao.delete(restCollectionVO);
	}

	@Override
	public List<RestCollectionVO> findByMemId(Integer memId) {
		return dao.findByMemId(memId);
	}

	@Override
	public List<RestCollectionVO> getAll() {
		return dao.getAll();
	}
	
	
}
