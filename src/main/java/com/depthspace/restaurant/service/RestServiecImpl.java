package com.depthspace.restaurant.service;

import java.util.List;

import org.hibernate.Session;

import com.depthspace.restaurant.model.restaurant.RestDAO;
import com.depthspace.restaurant.model.restaurant.RestDAOHibernateImpl;
import com.depthspace.restaurant.model.restaurant.RestDAOImpl;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.utils.HibernateUtil;

public class RestServiecImpl implements RestService {

	private RestDAO dao;
	
	public RestServiecImpl() {
		dao = new RestDAOHibernateImpl();
//		dao = new RestDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public RestVO addRest(RestVO restVO) {
		dao.add(restVO);
		return null;
	}

	@Override
	public RestVO updateRest(RestVO restVO) {
		return null;
	}

	@Override
	public void deleteRest(Integer restId) {
		dao.delete(restId);
	}

	@Override
	public RestVO getRestByRestId(Integer restId) {
		return dao.findByPK(restId);
	}

	@Override
	public List<RestVO> getAllRest() {
		return dao.getAll();
	}
	
}
