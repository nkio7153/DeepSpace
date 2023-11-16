package com.depthspace.column.service;

import java.util.List;

import com.depthspace.column.dao.ColumnTypesDAO;
import com.depthspace.column.dao.ColumnTypesDAOImpl;
import com.depthspace.column.model.ColumnTypesVO;
import com.depthspace.utils.HibernateUtil;

public class ColumnTypesServiceImpl implements ColumnTypesService {

	private ColumnTypesDAO dao;
	public ColumnTypesServiceImpl() {
		dao = new ColumnTypesDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public Integer insert(ColumnTypesVO columnTypesVO) {
		return dao.insert(columnTypesVO);
	}

	@Override
	public void update(ColumnTypesVO columnTypesVO) {
		dao.update(columnTypesVO);
	}

	@Override
	public List<ColumnTypesVO> getAll() {
		return dao.getAll();
	}

	@Override
	public ColumnTypesVO getOneById(Integer colTypVO) {
		return dao.getOneById(colTypVO);
	}

}
