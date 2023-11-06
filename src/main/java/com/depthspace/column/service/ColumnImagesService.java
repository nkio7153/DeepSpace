package com.depthspace.column.service;

import com.depthspace.column.model.ColumnImagesVO;

public interface ColumnImagesService {
	
	ColumnImagesVO save(ColumnImagesVO colImg);
	
	ColumnImagesVO getMainImageByArticleId(Integer artiId);
	
	void updateImg(ColumnImagesVO colImg);
}