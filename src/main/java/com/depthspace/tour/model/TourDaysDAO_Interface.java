package com.depthspace.tour.model;

import java.util.List;

public interface TourDaysDAO_Interface {
	public void insert(TourDaysVO TourDaysVO);

	public void update(TourDaysVO TourDaysVO);

	public void delete(Integer tourDaysId);

	public TourDaysVO findByPrimaryKey(Integer tourDaysId);

	public List<TourDaysVO> getAll();
}
