package com.depthspace.function.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.depthspace.function.model.FunctionDAO;
import com.depthspace.function.model.FunctionDAOImpl;
import com.depthspace.function.model.FunctionVO;
import com.depthspace.utils.HibernateUtil;
import com.depthspace.utils.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class FunctionServiceImpl implements FunctionService {

	public static final int PAGE_MAX_RESULT = 10;
	private FunctionDAO dao;

	public FunctionServiceImpl() {
		dao = new FunctionDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public FunctionVO add(FunctionVO functionVO) {
		if (functionVO != null) {
            dao.insert(functionVO);
        }
        return functionVO;
	}

	@Override
	public FunctionVO update(FunctionVO functionVO) {
		 if (functionVO != null) {
	            dao.update(functionVO);
	        }
	        return functionVO;
	}

	@Override
	public void delete(Integer funcId) {
		if (funcId != null) {
            dao.delete(funcId);
        }
	}

	@Override
	public FunctionVO getOneFunction(Integer funcId) {
		return funcId != null ? dao.findByPrimaryKey(funcId) : null;
	}

	@Override
	public List<FunctionVO> getAll() {
		return dao.getAll();
	}
}