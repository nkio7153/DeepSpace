package com.depthspace.tour.model.tour.hibernate;

import java.util.List;

import com.depthspace.tour.model.tour.TourVO;

public interface HbTourDAO_Interface {
	int insert(TourVO entity);
	int update(TourVO entity);
	int delete(Integer tourId);
	//應該要用tourId+memId去刪除 否則刪除一個就會全刪掉
	int delete(Integer tourId, Integer memId);
	List<TourVO> getAll();
	//取得該會員行程資料
	List<TourVO> getByMemId(Integer memId);

	
}
