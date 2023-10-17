package com.depthspace.restaurant.service;

import java.util.List;

import com.depthspace.restaurant.model.restaurant.RestDAO_interface;
import com.depthspace.restaurant.model.restaurant.RestJDBCDAOImpl;
import com.depthspace.restaurant.model.restaurant.RestVO;

public class RestServiecImpl implements RestService {

	private RestDAO_interface dao;
	
	public RestServiecImpl() {
		dao = new RestJDBCDAOImpl();
	}
	
	@Override
	public RestVO addRest(RestVO restVO) {
		return null;
	}

	@Override
	public RestVO updateRest(RestVO restVO) {
		return null;
	}

	@Override
	public void deleteRest(Integer restId) {
		
	}

	@Override
	public RestVO getRestByRestId(Integer restId) {
		return dao.findByPrimaryKey(restId);
	}

	@Override
	public List<RestVO> getAllRest() {
		return dao.getAll();
	}
	
}
