package com.depthspace.column.dao;

import com.depthspace.column.model.ColumnImagesVO;

public interface ColumnImagesDAO {
	
	void add(ColumnImagesVO colImg);
	
    ColumnImagesVO findByArticleId(Integer artiId);
    
    void updateImg(ColumnImagesVO colImg);
}