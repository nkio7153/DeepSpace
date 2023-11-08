package com.depthspace.attractions.model.attractions.hibernate;

import java.util.List;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.AttractionsVO;

public interface AttractionsDAO_Interface {
	public void insert(AttractionsVO AttractionsVO);

	public int update(AttractionsVO AttractionsVO);

	public void delete(Integer attractionsId);

	public AttractionsVO findByPrimaryKey(Integer attractionsId);

	public List<AttractionsVO> getAll();

	public List<AttractionsVO> getAllAttractions(Integer attractionsId);

	public List<AttractionsVO> findOneAttractions();

}