package com.depthspace.column.service;

import java.util.List;
import com.depthspace.column.model.ColumnTypesVO;

public interface ColumnTypesService {
	
	Integer insert(ColumnTypesVO columnTypesVO);
    
    void update(ColumnTypesVO columnTypesVO);

	List<ColumnTypesVO> getAll();

	ColumnTypesVO getOneById(Integer colTypeId);
}

