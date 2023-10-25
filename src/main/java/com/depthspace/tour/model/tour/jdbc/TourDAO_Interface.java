package com.depthspace.tour.model.tour.jdbc;

import java.util.List;

import com.depthspace.tour.model.tour.TourVO;

public interface TourDAO_Interface {
	public void insert(TourVO Tourvo);
    public void update(TourVO Tourvo);
    public void delete(Integer tourId);
    public TourVO findByPrimaryKey(Integer tourId);
    public List<TourVO> getAll();
}
