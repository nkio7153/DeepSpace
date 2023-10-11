package com.depthspace.tour.model;

import java.util.List;

public interface TourDAO_Interface {
	public void insert(TourVO TourVO);
    public void update(TourVO TourVO);
    public void delete(Integer tourId);
    public TourVO findByPrimaryKey(Integer tourId);
    public List<TourVO> getAll();
}
