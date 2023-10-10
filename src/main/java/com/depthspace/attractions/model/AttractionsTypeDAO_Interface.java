package com.depthspace.attractions.model;

import java.util.List;

public interface AttractionsTypeDAO_Interface {
	public void insert(AttractionsTypeVO AttractionsTypeVO);
	 public void update(AttractionsTypeVO AttractionsTypeVO);
	 public void delete(Integer attractionsTypeId);
	 public AttractionsTypeVO findByPrimaryKey(Integer attractionsTypeId);
	 public List<AttractionsTypeVO> getAll();
}
