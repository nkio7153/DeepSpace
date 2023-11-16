package com.depthspace.column.dao;

import java.util.List;

import com.depthspace.column.model.ColumnTypesVO;

public interface ColumnTypesDAO {
	
	Integer insert(ColumnTypesVO columnTypesVOVO);
	
	void update(ColumnTypesVO columnTypesVOVO);
	
	List<ColumnTypesVO> getAll();
	
	ColumnTypesVO getOneById(Integer columnTypesVOVO);
	
}
