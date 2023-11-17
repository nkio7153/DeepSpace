package com.depthspace.attractions.model.attractions.hibernate;

import java.util.List;
import java.util.Map;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.AttractionsVO;

public interface AttractionsDAO_Interface {
	public void insert(AttractionsVO AttractionsVO);

	public int update(AttractionsVO AttractionsVO);

	public void delete(Integer attractionsId);
	//依據景點id找尋對應景點
	public AttractionsVO getAttractionsById(Integer attractionsId);

	public List<AttractionsVO> getAll();

	public List<AttractionsVO> getAllAttractions(Integer attractionsId);

	public List<AttractionsVO> findOneAttractions();

	List<AttractionsVO> findOtherAttractions(String cityName);

	public List<AttractionsVO> getAttractionsName(String attractionsName);

	long getTotal();
	
	List<AttractionsVO> getByCompositeQuery(Map<String, List<String>> map);

}