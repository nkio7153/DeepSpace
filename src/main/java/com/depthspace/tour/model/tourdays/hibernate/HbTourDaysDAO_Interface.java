package com.depthspace.tour.model.tourdays.hibernate;

import java.util.List;

import com.depthspace.tour.model.TourDaysVO;

public interface HbTourDaysDAO_Interface {
	public int insert(TourDaysVO entity);

	public void update(TourDaysVO entity);

	public void delete(Integer tourDaysId);

	public TourDaysVO findByPrimaryKey(Integer tourDaysId);

	public List<TourDaysVO> getAll();
	
	public List<TourDaysVO> getOneTour(Integer tourId);
}
