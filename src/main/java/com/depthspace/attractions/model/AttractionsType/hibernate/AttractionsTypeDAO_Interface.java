package com.depthspace.attractions.model.AttractionsType.hibernate;

import java.util.List;

import com.depthspace.attractions.model.AttractionsTypeVO;

public interface AttractionsTypeDAO_Interface {
	public void insert(AttractionsTypeVO AttractionsTypeVO);
	 public void update(AttractionsTypeVO AttractionsTypeVO);
	 public void delete(Integer attractionsTypeId);
	 public AttractionsTypeVO findByPrimaryKey(Integer attractionsTypeId);
	 public List<AttractionsTypeVO> getAll();
}
