package com.depthspace.column.dao;

import java.util.List;

import com.depthspace.column.model.ColumnArticlesVO;

public interface ColumnArticlesDAO {
	
	void insert(ColumnArticlesVO entity);

	void update(ColumnArticlesVO entity);
	
	void delete(Integer id);
	
	long getTotal();
	
	List<ColumnArticlesVO> getAll(int currentPage);
	
	List<ColumnArticlesVO> getAll();

	ColumnArticlesVO getById(Integer id);
	
		
}
