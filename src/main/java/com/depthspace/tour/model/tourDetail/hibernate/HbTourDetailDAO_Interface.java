package com.depthspace.tour.model.tourDetail.hibernate;

import java.util.List;

import com.depthspace.tour.model.TourDetailVO;

public interface HbTourDetailDAO_Interface {
	public void insert(TourDetailVO TourDetailVO);
	public void update(TourDetailVO tourDetail);
	public void delete(Integer tourDaysId, Integer attractionsId);
	public void deleteByTourDaysId(Integer tourDaysId);
	public TourDetailVO findByPrimaryKey(Integer tourDaysId, Integer attractionsId);
//	public List<TourDetailVO> findByMemId(Integer memId);
    public List<TourDetailVO> getAll();
    public List<TourDetailVO> getTourDaysId(Integer tourDaysId);

}
