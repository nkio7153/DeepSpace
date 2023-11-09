package com.depthspace.attractions.model.attractions;

import java.util.List;

import com.depthspace.attractions.model.AttractionsVO;

public interface AttractionsDAO_Interface {
	 public void insert(AttractionsVO entity);
	 public int update(AttractionsVO entity);
	 public void delete(Integer attractionsId);
	 public AttractionsVO findByPrimaryKey(Integer attractionsId);
	 public List<AttractionsVO> getAll();
}
