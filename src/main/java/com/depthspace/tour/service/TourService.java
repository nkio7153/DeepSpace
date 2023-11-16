package com.depthspace.tour.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.depthspace.tour.model.TourDaysVO;
import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tour.TourView;
import com.depthspace.tour.model.tour.hibernate.HbTourDAOImpl;
import com.depthspace.tour.model.tour.hibernate.HbTourDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class TourService implements TourService_Interface{
	private HbTourDAO_Interface dao;

	public TourService() {
		dao = new HbTourDAOImpl(HibernateUtil.getSessionFactory());
	}
	@Override
	public TourVO insert(TourVO entity) {
//		System.out.println(entity.getMemId()); //1
		//先新增一筆會員行程資料
		dao.insert(entity);
		TourVO tourvo = null;
//		再找出會員資料的最後一筆(就會是剛才新增的行程)，把值帶到下一個頁面
		tourvo= dao.getLastTourId(entity.getTourId());
//		System.out.println("tourvo= " + tourvo.getTourId());
		//new 一個list集合把所有天數及會員編號放進去
		List<TourDaysVO> tourDaysVO = new ArrayList<>();
		TourDaysVO tdvo = new TourDaysVO();
		tdvo.setTourId(tourvo.getTourId());
		tdvo.setTourDays(tourvo.getAllDays());
		tourDaysVO.add(tdvo);
//		System.out.println("tdvo= " + tdvo);
		return tourvo;
	}

	@Override
	public int update(TourVO entity) {
		   dao.update(entity);
		   return 0;
	}

	@Override
	public int delete(Integer tourId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TourVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TourVO> getByMemId(Integer memId) {
		return dao.getByMemId(memId);
	}
	
	@Transactional
	public List<TourView> getOneTourList(Integer tourId,Integer memId) {
			return dao.getOneTourList(tourId,memId);
	}
	@Override
	public List<TourVO> getOneByMemId(Integer tourId, Integer memId) {
		return dao.getOneByMemId(tourId, memId);
	}	
	
}
