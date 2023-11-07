package com.depthspace.tour.model.tourtype.hibernate;

import java.util.List;

import com.depthspace.tour.model.tourtype.TourTypeVO;

public interface HbTourTypeDAO_Interface {
	public void insert(TourTypeVO entity);
    public void update(TourTypeVO entity);
    public void delete(Integer tourTypeId);
    public TourTypeVO findByPrimaryKey(Integer tourTypeId);
    public List<TourTypeVO> getAll();
}
