package com.depthspace.restaurant.service;

import java.util.List;
import java.util.Map;

import com.depthspace.restaurant.model.restaurant.RestDAO;
import com.depthspace.restaurant.model.restaurant.RestDAOImpl;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.utils.HibernateUtil;

public class RestServiecImpl implements RestService {

	private RestDAO dao;
	
	public RestServiecImpl() {
		dao = new RestDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public int addRest(RestVO restVO) {
		return dao.add(restVO);
		
	}

	@Override
	public RestVO updateRest(RestVO restVO) {
		dao.update(restVO);
		return restVO;
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
	public RestVO findByAdmin(Integer adminId) {
		return dao.findByAdmin(adminId);
	}

	@Override
	public List<RestVO> getAllRest() {
		return dao.getAll();
	}

	@Override
	public List<RestVO> showRest() {
		return dao.showRest();
	}

	@Override
	public List<RestVO> sreach(Map<String, String> map) {
		return dao.sreach(map);
	}

	

	
}
