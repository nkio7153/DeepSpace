package com.depthspace.tour.service;

import java.util.ArrayList;
import java.util.List;

import com.depthspace.tour.model.TourDaysVO;
import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tourdays.hibernate.HbTourDaysDAOImpl;
import com.depthspace.tour.model.tourdays.hibernate.HbTourDaysDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class TourDaysService{
	private HbTourDaysDAO_Interface dao;
	
	public TourDaysService() {
		dao = new HbTourDaysDAOImpl(HibernateUtil.getSessionFactory());
	}

	
	public List<TourDaysVO> insert(TourDaysVO entity) {
		dao.insert(entity);
		
		//在這邊回傳TourDays新增的最新Id
		List<TourDaysVO> tourDays = null;
//		再找出會員資料的最後一筆(就會是剛才新增的行程)，把值帶到下一個頁面
		tourDays= dao.getLastTourId(entity.getTourId());
		System.out.println("tourvo= " + tourDays);
		//new 一個list集合把所有天數及會員編號放進去
//		List<TourDaysVO> tourDaysVO = new ArrayList<>();
//		TourDaysVO tdvo = new TourDaysVO();
//		tdvo.setTourId(tourDays.getTourId());
//		tdvo.setTourDays(tourDays.getAllDays());
//		tourDaysVO.add(tdvo);
//		System.out.println("tdvo= " + tdvo);
		return tourDays;
	}

	public void update(TourDaysVO entity) {
		// TODO Auto-generated method stub
		
	}


	public void delete(Integer tourDaysId) {
		// TODO Auto-generated method stub
		
	}


	public TourDaysVO findByPrimaryKey(Integer tourDaysId) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<TourDaysVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	//找會員某行程的對應天數值(行程幾天就會是幾天的TourDaysId)-ok

	public List<TourDaysVO> getOneTour(Integer tourId) {
		return dao.getOneTour(tourId);
	}


	
	
	
}
