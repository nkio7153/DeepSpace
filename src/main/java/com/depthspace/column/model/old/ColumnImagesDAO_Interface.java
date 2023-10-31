package com.depthspace.column.model.old;

import java.util.List;

import com.depthspace.column.model.ColumnImagesVO;

public interface ColumnImagesDAO_Interface {
    
	public void insert(ColumnImagesVO columnImagesVO);
	public void update(ColumnImagesVO columnImagesVO);    
	public void delete(Integer colImgId);    
	public ColumnImagesVO findByPrimaryKey(Integer colImgId);
	public List<ColumnImagesVO> getAll();
}
