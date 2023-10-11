package com.depthspace.tour.model;

import java.util.List;

public interface TourDetailDAO_Interface {
	public void insert(TourDetailVO TourDetailVO);
	public void update(TourDetailVO tourDetail);
	public void delete(Integer tourDaysId, Integer attractionsId);
	public TourDetailVO findByPrimaryKey(Integer tourDaysId, Integer attractionsId);
//	public List<TourDetailVO> findByMemId(Integer memId);
    public List<TourDetailVO> getAll();

}
