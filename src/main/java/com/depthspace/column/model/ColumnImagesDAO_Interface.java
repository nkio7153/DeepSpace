package com.depthspace.column.model;

import java.util.List;

public interface ColumnImagesDAO_Interface {
    
	public void insert(ColumnImagesVO columnImagesVO);
	public void update(ColumnImagesVO columnImagesVO);    
	public void delete(Integer colImgId);    
	public ColumnImagesVO findByPrimaryKey(Integer colImgId);
	public List<ColumnImagesVO> getAll();
}
