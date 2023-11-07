package com.depthspace.tour.service;

import java.util.List;

import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tourtype.TourTypeVO;

public interface TourTypeService_Interface {
	int insert(TourTypeVO entity);
	int update(TourTypeVO entity);
	//可不用
	int delete(Integer tourTypeId);
	//取得所有行程種類
	List<TourTypeVO> getAll();
	TourTypeVO findByPrimaryKey(Integer tourTypeId);

}
