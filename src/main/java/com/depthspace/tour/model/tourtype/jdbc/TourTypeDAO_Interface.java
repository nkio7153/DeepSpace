package com.depthspace.tour.model.tourtype.jdbc;

import java.util.List;

import com.depthspace.tour.model.tourtype.TourTypeVO;

public interface TourTypeDAO_Interface {
	public void insert(TourTypeVO TourTypeVO);
    public void update(TourTypeVO TourTypeVO);
    public void delete(Integer tourTypeId);
    public TourTypeVO findByPrimaryKey(Integer tourTypeId);
    public List<TourTypeVO> getAll();
}
