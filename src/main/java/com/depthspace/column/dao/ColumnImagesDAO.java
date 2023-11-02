package com.depthspace.column.dao;

import com.depthspace.column.model.ColumnImagesVO;

public interface ColumnImagesDAO {
	
	int add(ColumnImagesVO columnImagesVO);
	
    ColumnImagesVO findByArticleId(Integer artiId);
    
    void updateImg(ColumnImagesVO colImg);
}
