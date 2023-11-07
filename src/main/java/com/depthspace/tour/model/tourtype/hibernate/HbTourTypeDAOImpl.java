package com.depthspace.tour.model.tourtype.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tourtype.TourTypeVO;

public class HbTourTypeDAOImpl implements HbTourTypeDAO_Interface{
	//宣告一個factory變數
    private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public HbTourTypeDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
    
    
	@Override
	public void insert(TourTypeVO entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(TourTypeVO entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer tourTypeId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public TourTypeVO findByPrimaryKey(Integer tourTypeId) {
		return getSession().get(TourTypeVO.class, tourTypeId);
	}
	@Override
	public List<TourTypeVO> getAll() {
		return getSession().createQuery("from TourTypeVO",TourTypeVO.class).list();
	}
}
