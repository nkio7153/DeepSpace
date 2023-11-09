package com.depthspace.tour.model.tourdays.hibernate;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.tour.model.TourDaysVO;
import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tour.TourView;

public class HbTourDaysDAOImpl implements HbTourDaysDAO_Interface{
	//宣告一個factory變數
    private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public HbTourDaysDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
    //新增行程
	@Override
	public int insert(TourDaysVO entity) {
		return (Integer)getSession().save(entity);
		
	}
	@Override
	public void update(TourDaysVO entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer tourDaysId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public TourDaysVO findByPrimaryKey(Integer tourDaysId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TourDaysVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
