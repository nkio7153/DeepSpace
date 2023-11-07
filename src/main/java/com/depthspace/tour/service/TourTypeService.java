package com.depthspace.tour.service;

import java.util.List;

import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tourtype.TourTypeVO;
import com.depthspace.tour.model.tourtype.hibernate.HbTourTypeDAOImpl;
import com.depthspace.tour.model.tourtype.hibernate.HbTourTypeDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class TourTypeService implements TourTypeService_Interface{
	private HbTourTypeDAO_Interface dao;
	
	public TourTypeService(){
		dao = new HbTourTypeDAOImpl(HibernateUtil.getSessionFactory());
	}
	@Override
	public int insert(TourTypeVO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(TourTypeVO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer tourTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TourTypeVO> getAll() {
		List<TourTypeVO> list = dao.getAll();
		return list;
	}
	
//	取得對應行程類型
	@Override
	public TourTypeVO findByPrimaryKey(Integer tourTypeId) {
		return dao.findByPrimaryKey(tourTypeId);
	}
	
	

}
