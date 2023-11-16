package com.depthspace.function.service;

import java.util.List;
import java.util.Map;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.column.model.ColumnTypesVO;
import com.depthspace.function.model.FunctionVO;


public interface FunctionService {
	
	FunctionVO add(FunctionVO functionVO);
	
	FunctionVO update(FunctionVO functionVO);
	
	void delete(Integer funcId);
	
	// 新增方法：根據功能ID獲取單個功能
	FunctionVO getOneFunction(Integer funcId);

	// 新增方法：獲取所有功能的列表
	List<FunctionVO> getAll();
}
