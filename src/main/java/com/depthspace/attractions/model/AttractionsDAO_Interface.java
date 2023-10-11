package com.depthspace.attractions.model;

import java.util.List;

public interface AttractionsDAO_Interface {
	 public void insert(AttractionsVO AttractionsVO);
	 public void update(AttractionsVO AttractionsVO);
	 public void delete(Integer attractionsId);
	 public AttractionsVO findByPrimaryKey(Integer attractionsId);
	 public List<AttractionsVO> getAll();
}
